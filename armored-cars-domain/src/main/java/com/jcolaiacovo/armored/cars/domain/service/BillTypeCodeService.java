package com.jcolaiacovo.armored.cars.domain.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.jcolaiacovo.armored.cars.domain.model.BillCode;
import com.jcolaiacovo.armored.cars.domain.model.BillType;
import com.jcolaiacovo.armored.cars.domain.model.BillTypeCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
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

    public List<BillTypeCode> getCollectionBillTypeCodes() {
        List<BillTypeCode> billTypeCodes = this.getAllByBillType(BillType.BILL);
        billTypeCodes.addAll(this.getAllByBillType(BillType.DEBIT_NOTE));

        return billTypeCodes;
    }

    public List<BillTypeCode> getPossibleBillTypeToApply(BillTypeCode billTypeCode) {
        if (BillType.CREDIT_NOTE.equals(billTypeCode.getBillType())) {
            return this.getByBillTypes(Sets.newHashSet(BillType.BILL, BillType.DEBIT_NOTE), billTypeCode.getBillCode());
        }
        return Lists.newArrayList();
    }

    private List<BillTypeCode> getAllByBillType(BillType billType) {
        return this.getBillTypeCodes(true).stream()
                .filter(billTypeCode -> billType.equals(billTypeCode.getBillType()))
                .collect(Collectors.toList());
    }

    private List<BillTypeCode> getByBillTypes(Set<BillType> billTypes, BillCode billCode) {
        return this.getBillTypeCodes(true).stream()
                .filter(billTypeCode -> billTypes.contains(billTypeCode.getBillType()))
                .filter(billTypeCode -> billCode.equals(billTypeCode.getBillCode()))
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
