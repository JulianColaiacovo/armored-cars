package com.jcolaiacovo.armored.cars.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import static com.jcolaiacovo.armored.cars.domain.model.BillCode.*;
import static com.jcolaiacovo.armored.cars.domain.model.BillType.*;

/**
 * Created by Julian on 09/01/2017.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum BillTypeCode {

    BILL_A(BILL, A, true),
    BILL_B(BILL, B, true),
    BILL_C(BILL, C, false),
    BILL_E(BILL, E, false),
    BILL_M(BILL, M, false),
    CREDIT_NOTE_A(CREDIT_NOTE, A, true),
    CREDIT_NOTE_B(CREDIT_NOTE, B, true),
    CREDIT_NOTE_C(CREDIT_NOTE, C, false),
    CREDIT_NOTE_E(CREDIT_NOTE, E, false),
    CREDIT_NOTE_M(CREDIT_NOTE, M, false),
    DEBIT_NOTE_A(DEBIT_NOTE, A, true),
    DEBIT_NOTE_B(DEBIT_NOTE, B, true),
    DEBIT_NOTE_C(DEBIT_NOTE, C, false),
    DEBIT_NOTE_E(DEBIT_NOTE, E, false),
    DEBIT_NOTE_M(DEBIT_NOTE, M, false);

    private BillType billType;
    private BillCode billCode;
    private boolean active;

    BillTypeCode(BillType billType, BillCode billCode, boolean active) {
        this.billType = billType;
        this.billCode = billCode;
        this.active = active;
    }

    public BillType getBillType() {
        return billType;
    }

    public BillCode getBillCode() {
        return billCode;
    }

    public boolean isActive() {
        return active;
    }

}
