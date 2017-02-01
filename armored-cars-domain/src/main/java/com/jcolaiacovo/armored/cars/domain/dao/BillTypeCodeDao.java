package com.jcolaiacovo.armored.cars.domain.dao;

import com.jcolaiacovo.armored.cars.domain.model.BillTypeCode;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Julian on 29/01/2017.
 */
@Repository
public class BillTypeCodeDao extends AbstractDao {

    @Autowired
    public BillTypeCodeDao(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<BillTypeCode> getBillTypeCodes(Boolean enabled) {
        if (enabled == null) {
            return this.getAllBillTypeCodes();
        } else if (enabled) {
            return this.getEnabledBillTypeCodes();
        } else {
            return this.getDisabledBillTypeCodes();
        }
    }

    private List<BillTypeCode> getAllBillTypeCodes() {
        return (List<BillTypeCode>) this.getSessionFactory().getCurrentSession().createSQLQuery("select * from BILL_TYPE_CODE")
                .addEntity(BillTypeCode.class)
                .list();
    }

    private List<BillTypeCode> getEnabledBillTypeCodes() {
        return (List<BillTypeCode>) this.getSessionFactory().getCurrentSession().createSQLQuery("select * from BILL_TYPE_CODE where enabled")
                .addEntity(BillTypeCode.class)
                .list();
    }

    private List<BillTypeCode> getDisabledBillTypeCodes() {
        return (List<BillTypeCode>) this.getSessionFactory().getCurrentSession().createSQLQuery("select * from BILL_TYPE_CODE where not enabled")
                .addEntity(BillTypeCode.class)
                .list();
    }

}