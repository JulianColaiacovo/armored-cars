package com.jcolaiacovo.armored.cars.domain.service;

import com.jcolaiacovo.armored.cars.domain.dao.AbstractDao;
import com.jcolaiacovo.armored.cars.domain.dao.AdditionalDao;
import com.jcolaiacovo.armored.cars.domain.model.Additional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Julian on 21/02/2017.
 */
@Transactional
@Service
public class AdditionalService extends AbstractDaoService<Additional> {

    private AdditionalDao additionalDao;

    @Autowired
    public AdditionalService(AdditionalDao additionalDao) {
        this.additionalDao = additionalDao;
    }

    public List<Additional> getAll() {
        return this.additionalDao.getAll();
    }

    @Override
    protected AbstractDao<Additional> getDao() {
        return this.additionalDao;
    }

}
