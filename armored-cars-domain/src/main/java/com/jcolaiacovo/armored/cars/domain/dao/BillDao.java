package com.jcolaiacovo.armored.cars.domain.dao;

import com.jcolaiacovo.armored.cars.domain.model.Bill;
import com.jcolaiacovo.armored.cars.domain.model.BillTypeCode;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Created by Julian on 03/02/2017.
 */
@Repository
public class BillDao extends AbstractDao<Bill> {

    @Autowired
    public BillDao(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
        super(Bill.class, sessionFactory);
    }

    public Optional<Bill> getLastBill(BillTypeCode billTypeCode) {
        return this.getSessionFactory().getCurrentSession()
                .createQuery("select distinct bill from Bill as bill where bill.billTypeCode = :billTypeCode order by bill.number desc")
                .setString("billTypeCode", billTypeCode.name())
                .list()
                .stream()
                .map(Bill.class::cast)
                .findFirst();
    }

    public List<Bill> getByClientId(int clientId) {
        return this.getSessionFactory().getCurrentSession()
                .createQuery("select distinct bill from Bill as bill where bill.billTo.id = :billToId order by bill.number desc")
                .setInteger("billToId", clientId)
                .list();
    }

    public List<Bill> search(BillTypeCode billTypeCode, String clientName) {
        String likeClientName = Optional.ofNullable(clientName).map(s -> '%' + s + '%').orElse("%");
        return this.getSessionFactory().getCurrentSession()
                .createQuery("select distinct bill from Bill as bill " +
                        " where bill.billTypeCode = :billTypeCode " +
                        "       and bill.billTo.name like :clientName" +
                        " order by bill.number desc")
                .setString("billTypeCode", billTypeCode.name())
                .setString("clientName", likeClientName)
                .list();
    }

    public List<Bill> getUncollectedBills() {
        return this.getSessionFactory().getCurrentSession()
                .createQuery("select distinct B from Bill as B where B.totalAmount != " +
                        "ifnull((select sum(AB.totalAmount) from Bill as AB where B.id = AB.applyBill.id), 0) + " +
                        "ifnull((select sum(C.totalAmount) from Collection as C where B.id = C.bill.id), 0)")
                .list();
    }

    public BigDecimal getCreditNoteAmount(int billId) {
        List<BigDecimal> collections = this.getSessionFactory().getCurrentSession()
                .createQuery("select bill.totalAmount from Bill as bill where bill.applyBill.id = :billId")
                .setInteger("billId", billId)
                .list();
        return collections.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Bill> getBillsByArmoredId(int armoredId) {
        return this.getSessionFactory().getCurrentSession()
                .createQuery("select distinct bill from Bill as bill where bill.armored.id = :armoredId order by bill.number desc")
                .setInteger("armoredId", armoredId)
                .list();
    }

    public List<Bill> getBillsByApplyBillId(int billId) {
        return this.getSessionFactory().getCurrentSession()
                .createQuery("select distinct bill from Bill as bill where bill.applyBill.id = :billId order by bill.number desc")
                .setInteger("billId", billId)
                .list();
    }

    public List<Bill> getAll() {
        return (List<Bill>) this.getSessionFactory().getCurrentSession().createSQLQuery("select * from BILL;")
                .addEntity(Bill.class)
                .list();
    }

}
