package com.jcolaiacovo.armored.cars.domain.dao;

import com.google.common.collect.Lists;
import com.jcolaiacovo.armored.cars.domain.model.BillTypeCode;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Julian on 29/01/2017.
 */
@Repository
public class BillTypeCodeDao {

    public List<BillTypeCode> getBillTypeCodes(Boolean active) {
        if (active == null) {
            return this.getAllBillTypeCodes();
        } else if (active) {
            return this.getActiveBillTypeCodes();
        } else {
            return this.getDisabledBillTypeCodes();
        }
    }

    private List<BillTypeCode> getAllBillTypeCodes() {
        return Lists.newArrayList(BillTypeCode.values());
    }

    private List<BillTypeCode> getActiveBillTypeCodes() {
        return this.getAllBillTypeCodes().stream().filter(BillTypeCode::isActive).collect(Collectors.toList());
    }

    private List<BillTypeCode> getDisabledBillTypeCodes() {
        return this.getAllBillTypeCodes().stream().filter(billTypeCode -> !billTypeCode.isActive()).collect(Collectors.toList());
    }

}