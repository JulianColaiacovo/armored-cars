package com.jcolaiacovo.armored.cars.domain.service;

import com.jcolaiacovo.armored.cars.domain.model.BillTypeCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Julian on 09/01/2017.
 */
@Transactional
@Service
public class BillTypeCodeService {

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
        return BillTypeCode.getAllBillTypeCodes();
    }

    private List<BillTypeCode> getActiveBillTypeCodes() {
        return this.getAllBillTypeCodes().stream().filter(BillTypeCode::isActive).collect(Collectors.toList());
    }

    private List<BillTypeCode> getDisabledBillTypeCodes() {
        return this.getAllBillTypeCodes().stream().filter(billTypeCode -> !billTypeCode.isActive()).collect(Collectors.toList());
    }

}
