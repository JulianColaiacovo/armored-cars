package com.jcolaiacovo.armored.cars.domain.service;

import com.jcolaiacovo.armored.cars.domain.dao.AbstractDao;
import com.jcolaiacovo.armored.cars.domain.dao.BillDao;
import com.jcolaiacovo.armored.cars.domain.model.Bill;
import com.jcolaiacovo.armored.cars.domain.model.BillTypeCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Julian on 15/03/2017.
 */
@Transactional
@Service
public class BillService extends AbstractDaoService<Bill> {

    private static final long INITIAL_BILL_NUMBER = 100000001L;

    private BillDao billDao;

    @Autowired
    public BillService(BillDao billDao) {
        this.billDao = billDao;
    }

    public long getNextBillNumber(BillTypeCode billTypeCode) {
        return this.billDao.getLastBill(billTypeCode).map(Bill::getNumber).map(number -> number + 1).orElse(INITIAL_BILL_NUMBER);
    }

    public List<Bill> getAll() {
        return this.billDao.getAll();
    }

    @Override
    protected AbstractDao<Bill> getDao() {
        return this.billDao;
    }

}
