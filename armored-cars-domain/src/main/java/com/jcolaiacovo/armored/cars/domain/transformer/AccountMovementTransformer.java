package com.jcolaiacovo.armored.cars.domain.transformer;

import com.jcolaiacovo.armored.cars.api.model.AccountMovementDTO;
import com.jcolaiacovo.armored.cars.domain.accountbalance.AccountMovement;
import org.springframework.stereotype.Component;

/**
 * Created by Julian on 10/04/2017.
 */
@Component
public class AccountMovementTransformer extends AbstractApiTransformer<AccountMovement, AccountMovementDTO> {


    @Override
    public AccountMovement transform(AccountMovementDTO value) {
        return new AccountMovement(value.getDebit(), value.getCredit(), value.getDateTime());
    }

    @Override
    public AccountMovementDTO transformToDTO(AccountMovement value) {
        AccountMovementDTO accountMovementDTO = new AccountMovementDTO();

        accountMovementDTO.setDebit(value.getDebit());
        accountMovementDTO.setCredit(value.getCredit());
        accountMovementDTO.setDateTime(value.getDateTime());

        return accountMovementDTO;
    }

}
