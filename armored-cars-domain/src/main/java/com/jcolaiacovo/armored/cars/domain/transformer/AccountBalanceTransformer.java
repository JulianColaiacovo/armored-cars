package com.jcolaiacovo.armored.cars.domain.transformer;

import com.jcolaiacovo.armored.cars.api.model.AccountBalanceDTO;
import com.jcolaiacovo.armored.cars.api.model.AccountMovementDTO;
import com.jcolaiacovo.armored.cars.domain.accountbalance.AccountBalance;
import com.jcolaiacovo.armored.cars.domain.accountbalance.AccountMovement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Julian on 10/04/2017.
 */
@Component
public class AccountBalanceTransformer extends AbstractApiTransformer<AccountBalance, AccountBalanceDTO> {

    private AccountMovementTransformer accountMovementTransformer;

    @Autowired
    public AccountBalanceTransformer(AccountMovementTransformer accountMovementTransformer) {
        this.accountMovementTransformer = accountMovementTransformer;
    }

    @Override
    public AccountBalance transform(AccountBalanceDTO value) {
        List<AccountMovement> accountMovements = this.accountMovementTransformer.transformAll(value.getAccountMovements());
        return new AccountBalance(accountMovements);
    }

    @Override
    public AccountBalanceDTO transformToDTO(AccountBalance value) {
        AccountBalanceDTO accountBalanceDTO = new AccountBalanceDTO();

        List<AccountMovementDTO> accountMovements = this.accountMovementTransformer.transformToDTOAll(value.getAccountMovements());
        accountBalanceDTO.setAccountMovements(accountMovements);
        accountBalanceDTO.setBalance(value.getBalance());

        return accountBalanceDTO;
    }

}
