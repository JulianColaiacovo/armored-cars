package com.jcolaiacovo.armored.cars.service;

import com.jcolaiacovo.armored.cars.domain.dao.AbstractDao;
import com.jcolaiacovo.armored.cars.domain.dao.CurrencyDao;
import com.jcolaiacovo.armored.cars.domain.model.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Julian on 09/01/2017.
 */
@Transactional
@Service
public class CurrencyService extends AbstractDaoService<Currency> {

    private CurrencyDao currencyDao;

    @Autowired
    public CurrencyService(CurrencyDao currencyDao) {
        this.currencyDao = currencyDao;
    }

    public List<Currency>  getAllCurrencies() {
        return this.currencyDao.getAllCurrencies();
    }

    @Override
    protected AbstractDao<Currency> getDao() {
        return this.currencyDao;
    }

}
