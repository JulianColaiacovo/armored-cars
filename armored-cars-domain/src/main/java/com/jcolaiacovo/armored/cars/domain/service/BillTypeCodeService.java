package com.jcolaiacovo.armored.cars.domain.service;

import com.jcolaiacovo.armored.cars.domain.model.Bill;
import com.jcolaiacovo.armored.cars.domain.model.BillType;
import com.jcolaiacovo.armored.cars.domain.model.BillTypeCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.testng.collections.Lists;

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

    public List<BillTypeCode> getPossibleBillTypeToApply(BillTypeCode billTypeCode) {
        if (BillType.CREDIT_NOTE.equals(billTypeCode.getBillType())) {
            return this.getAllByBillType(BillType.BILL);
        }
        return Lists.newArrayList();
    }

    private List<BillTypeCode> getAllByBillType(BillType billType) {
        return this.getBillTypeCodes(true).stream()
                .filter(billTypeCode -> billType.equals(billTypeCode.getBillType()))
                .collect(Collectors.toList());
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
