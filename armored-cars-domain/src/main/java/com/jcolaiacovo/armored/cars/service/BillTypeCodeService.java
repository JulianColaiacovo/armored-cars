package com.jcolaiacovo.armored.cars.service;

import com.jcolaiacovo.armored.cars.domain.dao.BillTypeCodeDao;
import com.jcolaiacovo.armored.cars.domain.model.BillTypeCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Julian on 09/01/2017.
 */
@Transactional
@Service
public class BillTypeCodeService {

    private BillTypeCodeDao billTypeCodeDao;

    @Autowired
    public BillTypeCodeService(BillTypeCodeDao billTypeCodeDao) {
        this.billTypeCodeDao = billTypeCodeDao;
    }

    public List<BillTypeCode> getBillTypeCodes(Boolean enabled) {
        return this.billTypeCodeDao.getBillTypeCodes(enabled);
    }

}
