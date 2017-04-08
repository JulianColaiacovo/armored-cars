package com.jcolaiacovo.armored.cars.domain.service;

import com.jcolaiacovo.armored.cars.domain.dao.AbstractDao;
import com.jcolaiacovo.armored.cars.domain.dao.BillingAndReferenceDao;
import com.jcolaiacovo.armored.cars.domain.model.BillingAndReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Julian on 14/01/2017.
 */
@Transactional
@Service
public class BillingAndReferenceService extends AbstractDaoService<BillingAndReference> {

    private BillingAndReferenceDao billingAndReferenceDao;

    @Autowired
    public BillingAndReferenceService(BillingAndReferenceDao billingAndReferenceDao) {
        this.billingAndReferenceDao = billingAndReferenceDao;
    }

    public List<BillingAndReference> getAll() {
        return this.billingAndReferenceDao.getAll();
    }

    @Override
    protected AbstractDao<BillingAndReference> getDao() {
        return this.billingAndReferenceDao;
    }

}
