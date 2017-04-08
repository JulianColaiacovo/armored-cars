package com.jcolaiacovo.armored.cars.domain.service;

import com.jcolaiacovo.armored.cars.domain.dao.AbstractDao;
import com.jcolaiacovo.armored.cars.domain.dao.CommissionDao;
import com.jcolaiacovo.armored.cars.domain.model.Commission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Julian on 14/01/2017.
 */
@Transactional
@Service
public class CommissionService extends AbstractDaoService<Commission> {

    private CommissionDao commissionDao;

    @Autowired
    public CommissionService(CommissionDao commissionDao) {
        this.commissionDao = commissionDao;
    }

    public List<Commission> getAll() {
        return this.commissionDao.getAll();
    }

    @Override
    protected AbstractDao<Commission> getDao() {
        return this.commissionDao;
    }

}
