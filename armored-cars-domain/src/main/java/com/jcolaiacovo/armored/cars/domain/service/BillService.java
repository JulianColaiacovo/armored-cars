package com.jcolaiacovo.armored.cars.domain.service;

import com.jcolaiacovo.armored.cars.domain.dao.AbstractDao;
import com.jcolaiacovo.armored.cars.domain.dao.BillDao;
import com.jcolaiacovo.armored.cars.domain.model.Bill;
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

    private BillDao billDao;

    @Autowired
    public BillService(BillDao billDao) {
        this.billDao = billDao;
    }

    public List<Bill> getAll() {
        return this.billDao.getAll();
    }

    @Override
    protected AbstractDao<Bill> getDao() {
        return this.billDao;
    }

}
