package com.jcolaiacovo.armored.cars.service;

import com.jcolaiacovo.armored.cars.domain.dao.AbstractDao;
import com.jcolaiacovo.armored.cars.domain.dao.ArmoredDao;
import com.jcolaiacovo.armored.cars.domain.model.Armored;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Julian on 21/02/2017.
 */
@Transactional
@Service
public class ArmoredService extends AbstractDaoService<Armored> {

    private ArmoredDao armoredDao;

    @Autowired
    public ArmoredService(ArmoredDao armoredDao) {
        this.armoredDao = armoredDao;
    }

    public List<Armored> getAllArmoreds() {
        return this.armoredDao.getAllArmoreds();
    }

    @Override
    protected AbstractDao<Armored> getDao() {
        return this.armoredDao;
    }

}
