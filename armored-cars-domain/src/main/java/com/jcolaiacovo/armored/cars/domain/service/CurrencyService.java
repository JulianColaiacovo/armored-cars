package com.jcolaiacovo.armored.cars.domain.service;

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

    public Currency getByCode(String code) {
        return this.currencyDao.getByCode(code);
    }

    public List<Currency> getCurrencies(Boolean enabled) {
        if (enabled == null) {
            return this.currencyDao.getAll();
        } else if (enabled) {
            return this.currencyDao.getEnabledCurrencies();
        } else {
            return this.currencyDao.getDisablesCurrencies();
        }
    }

    @Override
    protected AbstractDao<Currency> getDao() {
        return this.currencyDao;
    }

}
