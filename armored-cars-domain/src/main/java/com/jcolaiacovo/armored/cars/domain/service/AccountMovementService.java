package com.jcolaiacovo.armored.cars.domain.service;

import com.google.common.collect.Lists;
import com.jcolaiacovo.armored.cars.domain.converter.BillToAccountMovementConverter;
import com.jcolaiacovo.armored.cars.domain.converter.CollectionToAccountMovementConverter;
import com.jcolaiacovo.armored.cars.domain.accountbalance.AccountMovement;
import com.jcolaiacovo.armored.cars.domain.model.Bill;
import com.jcolaiacovo.armored.cars.domain.model.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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
        List<Collection> collections = this.collectionService.getByBillClientId(clientId);

        return this.convertBillsAndCollections(bills, collections);
    }

    public List<AccountMovement> getAccountMovementsByArmoredId(int armoredId) {
        List<Bill> bills = this.billService.getBillsByArmoredId(armoredId);
        List<Collection> collections = this.collectionService.getByArmoredId(armoredId);

        return this.convertBillsAndCollections(bills, collections);
    }

    public List<AccountMovement> convertBillsAndCollections(List<Bill> bills, List<Collection> collections) {
        List<AccountMovement> billAccountMovements = this.billToAccountMovementConverter.convertAll(bills);
        List<AccountMovement> collectionAccountMovements = this.collectionToAccountMovementConverter.convertAll(collections);

        List<AccountMovement> accountMovements = Lists.newArrayList();
        accountMovements.addAll(billAccountMovements);
        accountMovements.addAll(collectionAccountMovements);
        //menor a mayor
        accountMovements.sort(Comparator.comparing(AccountMovement::getDateTime));

        return accountMovements;
    }

}
