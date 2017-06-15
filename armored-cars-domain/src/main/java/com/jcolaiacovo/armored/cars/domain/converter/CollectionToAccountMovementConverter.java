package com.jcolaiacovo.armored.cars.domain.converter;

import com.jcolaiacovo.armored.cars.domain.accountbalance.AccountMovement;
import com.jcolaiacovo.armored.cars.domain.model.Collection;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by Julian on 15/06/2017.
 */
@Component
public class CollectionToAccountMovementConverter extends AbstractConverter<Collection, AccountMovement> {

    @Override
    public AccountMovement convert(Collection value) {
        return new AccountMovement(BigDecimal.ZERO, value.getTotalAmount(), value.getDate());
    }

}
