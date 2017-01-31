package com.jcolaiacovo.armored.cars.domain.model;

import java.math.BigDecimal;

/**
 * Created by Julian on 29/01/2017.
 */
public enum Aliquot {

    FIVE(BigDecimal.valueOf(5)),
    TEN_POINT_FIFTY(BigDecimal.valueOf(10.5)),
    TWENTY_ONE(BigDecimal.valueOf(21)),
    TWENTY_SEVEN(BigDecimal.valueOf(27));

    private BigDecimal value;

    Aliquot(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

}
