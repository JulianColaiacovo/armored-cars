package com.jcolaiacovo.armored.cars.domain.converter;

import com.jcolaiacovo.armored.cars.domain.accountbalance.AccountMovement;
import com.jcolaiacovo.armored.cars.domain.helper.BillHelper;
import com.jcolaiacovo.armored.cars.domain.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by Julian on 15/06/2017.
 */
@Component
public class BillToAccountMovementConverter extends AbstractConverter<Bill, AccountMovement> {

    private BillHelper billHelper;

    @Autowired
    public BillToAccountMovementConverter(BillHelper billHelper) {
        this.billHelper = billHelper;
    }

    @Override
    public AccountMovement convert(Bill bill) {
        BigDecimal usdAmount = bill.getTotalAmount().divide(bill.getConversion(), BigDecimal.ROUND_CEILING);
        if (this.billHelper.isCreditNote(bill)) {
            return new AccountMovement(bill.getCurrency().getCode(), BigDecimal.ZERO, usdAmount, bill.getId(),
                    null, bill.getDate());
        } else {
            return new AccountMovement(bill.getCurrency().getCode(), usdAmount, BigDecimal.ZERO, bill.getId(),
                    null, bill.getDate());
        }
    }

}
