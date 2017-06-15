package com.jcolaiacovo.armored.cars.domain.helper;

import com.jcolaiacovo.armored.cars.domain.model.Bill;
import com.jcolaiacovo.armored.cars.domain.model.BillType;
import com.jcolaiacovo.armored.cars.domain.model.BillTypeCode;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by Julian on 15/06/2017.
 */
@Component
public class BillHelper {

    public boolean isCreditNote(Bill bill) {
        return Optional.ofNullable(bill)
                .map(Bill::getBillTypeCode)
                .map(BillTypeCode::getBillType)
                .map(BillType.CREDIT_NOTE::equals)
                .orElse(false);
    }

}
