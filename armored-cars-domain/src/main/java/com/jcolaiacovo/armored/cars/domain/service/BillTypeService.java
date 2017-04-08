package com.jcolaiacovo.armored.cars.domain.service;

import com.google.common.collect.Lists;
import com.jcolaiacovo.armored.cars.domain.model.BillType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Julian on 29/01/2017.
 */
@Service
public class BillTypeService {

    public List<BillType> getAllBillTypes() {
        return Lists.newArrayList(BillType.values());
    }

}
