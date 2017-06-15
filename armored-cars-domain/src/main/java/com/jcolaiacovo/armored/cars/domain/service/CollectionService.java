package com.jcolaiacovo.armored.cars.domain.service;

import com.jcolaiacovo.armored.cars.domain.dao.AbstractDao;
import com.jcolaiacovo.armored.cars.domain.dao.CollectionDao;
import com.jcolaiacovo.armored.cars.domain.model.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Julian on 14/01/2017.
 */
@Transactional
@Service
public class CollectionService extends AbstractDaoService<Collection> {

    private CollectionDao collectionDao;

    @Autowired
    public CollectionService(CollectionDao collectionDao) {
        this.collectionDao = collectionDao;
    }

    public List<Collection> getAll() {
        return this.collectionDao.getAll();
    }

    public List<Collection> getByBillClientId(int clientId) {
        return this.collectionDao.getByBillClientId(clientId);
    }

    @Override
    protected AbstractDao<Collection> getDao() {
        return this.collectionDao;
    }

}
