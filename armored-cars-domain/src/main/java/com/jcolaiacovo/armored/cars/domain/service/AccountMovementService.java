package com.jcolaiacovo.armored.cars.domain.service;

import com.google.common.collect.Lists;
import com.jcolaiacovo.armored.cars.domain.converter.BillToAccountMovementConverter;
import com.jcolaiacovo.armored.cars.domain.converter.CollectionToAccountMovementConverter;
import com.jcolaiacovo.armored.cars.domain.accountbalance.AccountMovement;
import com.jcolaiacovo.armored.cars.domain.model.Bill;
import com.jcolaiacovo.armored.cars.domain.model.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Julian on 15/06/2017.
 */
@Service
public class AccountMovementService {

    private BillService billService;
    private CollectionService collectionService;
    private BillToAccountMovementConverter billToAccountMovementConverter;
    private CollectionToAccountMovementConverter collectionToAccountMovementConverter;

    @Autowired
    public AccountMovementService(BillService billService,
                                  CollectionService collectionService,
                                  BillToAccountMovementConverter billToAccountMovementConverter,
                                  CollectionToAccountMovementConverter collectionToAccountMovementConverter) {
        this.billService = billService;
        this.collectionService = collectionService;
        this.billToAccountMovementConverter = billToAccountMovementConverter;
        this.collectionToAccountMovementConverter = collectionToAccountMovementConverter;
    }

    public List<AccountMovement> getAccountMovementsByClientId(int clientId) {
        List<Bill> bills = this.billService.getBillsByClientId(clientId);
        List<AccountMovement> billAccountMovements = this.billToAccountMovementConverter.convertAll(bills);

        List<Collection> collections = this.collectionService.getByBillClientId(clientId);
        List<AccountMovement> collectionAccountMovements = this.collectionToAccountMovementConverter.convertAll(collections);

        List<AccountMovement> accountMovements = Lists.newArrayList();
        accountMovements.addAll(billAccountMovements);
        accountMovements.addAll(collectionAccountMovements);
        //mayor a menor
        accountMovements.sort((o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime()));

        return accountMovements;
    }

}
