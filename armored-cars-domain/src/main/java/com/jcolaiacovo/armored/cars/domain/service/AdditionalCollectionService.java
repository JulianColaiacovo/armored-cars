package com.jcolaiacovo.armored.cars.domain.service;

import com.jcolaiacovo.armored.cars.domain.dao.AbstractDao;
import com.jcolaiacovo.armored.cars.domain.dao.AdditionalCollectionDao;
import com.jcolaiacovo.armored.cars.domain.model.AdditionalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Julian on 21/02/2017.
 */
@Transactional
@Service
public class AdditionalCollectionService extends AbstractDaoService<AdditionalCollection> {

    private AdditionalCollectionDao additionalCollectionDao;

    @Autowired
    public AdditionalCollectionService(AdditionalCollectionDao additionalCollectionDao) {
        this.additionalCollectionDao = additionalCollectionDao;
    }

    public List<AdditionalCollection> getAll() {
        return this.additionalCollectionDao.getAll();
    }

    @Override
    protected AbstractDao<AdditionalCollection> getDao() {
        return this.additionalCollectionDao;
    }

}
