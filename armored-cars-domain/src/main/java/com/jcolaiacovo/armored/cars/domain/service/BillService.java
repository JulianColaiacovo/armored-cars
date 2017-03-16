package com.jcolaiacovo.armored.cars.service;

import com.jcolaiacovo.armored.cars.domain.dao.AbstractDao;
import com.jcolaiacovo.armored.cars.domain.dao.BillDao;
import com.jcolaiacovo.armored.cars.domain.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Julian on 15/03/2017.
 */
@Service
@Transactional
public class BillService extends AbstractDaoService<Bill> {

    private BillDao billDao;

    @Autowired
    public BillService(BillDao billDao) {
        this.billDao = billDao;
    }

    @Override
    protected AbstractDao<Bill> getDao() {
        return this.billDao;
    }

}
