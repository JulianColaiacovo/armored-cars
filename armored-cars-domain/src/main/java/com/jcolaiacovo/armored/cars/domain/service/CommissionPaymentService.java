package com.jcolaiacovo.armored.cars.domain.service;

import com.jcolaiacovo.armored.cars.domain.dao.AbstractDao;
import com.jcolaiacovo.armored.cars.domain.dao.CommissionPaymentDao;
import com.jcolaiacovo.armored.cars.domain.model.CommissionPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Julian on 14/01/2017.
 */
@Transactional
@Service
public class CommissionPaymentService extends AbstractDaoService<CommissionPayment> {

    private CommissionPaymentDao commissionPaymentDao;

    @Autowired
    public CommissionPaymentService(CommissionPaymentDao commissionPaymentDao) {
        this.commissionPaymentDao = commissionPaymentDao;
    }

    public List<CommissionPayment> getAll() {
        return this.commissionPaymentDao.getAll();
    }

    @Override
    protected AbstractDao<CommissionPayment> getDao() {
        return this.commissionPaymentDao;
    }

}
