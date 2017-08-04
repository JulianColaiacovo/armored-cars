package com.jcolaiacovo.armored.cars.domain.service;

import com.google.common.collect.Sets;
import com.jcolaiacovo.armored.cars.domain.dao.AbstractDao;
import com.jcolaiacovo.armored.cars.domain.dao.BillDao;
import com.jcolaiacovo.armored.cars.domain.model.Bill;
import com.jcolaiacovo.armored.cars.domain.model.BillType;
import com.jcolaiacovo.armored.cars.domain.model.BillTypeCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Julian on 15/03/2017.
 */
@Transactional
@Service
public class BillService extends AbstractDaoService<Bill> {

    private static final Set<BillType> UNCOLLECTED_BILL_TYPES = Sets.newHashSet(BillType.BILL, BillType.DEBIT_NOTE);
    private static final long INITIAL_BILL_NUMBER = 100000001L;

    private BillDao billDao;

    @Autowired
    public BillService(BillDao billDao) {
        this.billDao = billDao;
    }

    public long getNextBillNumber(BillTypeCode billTypeCode) {
        return this.billDao.getLastBill(billTypeCode).map(Bill::getNumber).map(number -> number + 1).orElse(INITIAL_BILL_NUMBER);
    }

    public List<Bill> getBillsByClientId(int clientId) {
        return this.billDao.getByClientId(clientId);
    }

    public List<Bill> search(BillTypeCode billTypeCode, String clientName) {
        return this.billDao.search(billTypeCode, clientName);
    }

    public List<Bill> getUncollectedBills() {
        return this.billDao.getUncollectedBills().stream()
                .filter(this::isUncollectedBillType)
                .collect(Collectors.toList());
    }

    public BigDecimal getCollectedAmount(int billId) {
        return this.billDao.getCollectedAmount(billId);
    }

    public List<Bill> getBillsByApplyBillId(int billId) {
        return this.billDao.getBillsByApplyBillId(billId);
    }

    public List<Bill> getBillsByArmoredId(int armoredId) {
        return this.billDao.getBillsByArmoredId(armoredId);
    }

    public List<Bill> getAll() {
        return this.billDao.getAll();
    }

    @Override
    protected AbstractDao<Bill> getDao() {
        return this.billDao;
    }

    private boolean isUncollectedBillType(Bill bill) {
        return UNCOLLECTED_BILL_TYPES.contains(bill.getBillTypeCode().getBillType());
    }

}
