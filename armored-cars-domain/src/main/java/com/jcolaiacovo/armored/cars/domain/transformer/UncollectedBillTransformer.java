package com.jcolaiacovo.armored.cars.domain.transformer;

import com.jcolaiacovo.armored.cars.api.model.UncollectedBillDTO;
import com.jcolaiacovo.armored.cars.domain.model.Bill;
import com.jcolaiacovo.armored.cars.domain.service.BillService;
import com.jcolaiacovo.armored.cars.domain.service.CollectionService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by Julian on 10/04/2017.
 */
@Component
public class UncollectedBillTransformer extends AbstractApiTransformer<Bill, UncollectedBillDTO> {

    private BillTransformer billTransformer;
    private BillService billService;
    private CollectionService collectionService;

    public UncollectedBillTransformer(BillTransformer billTransformer,
                                      BillService billService,
                                      CollectionService collectionService) {
        this.billTransformer = billTransformer;
        this.billService = billService;
        this.collectionService = collectionService;
    }

    @Override
    public Bill transform(UncollectedBillDTO value) {
        return null;
    }

    @Override
    public UncollectedBillDTO transformToDTO(Bill bill) {
        UncollectedBillDTO uncollectedBillDTO = new UncollectedBillDTO();

        uncollectedBillDTO.setBill(this.billTransformer.transformToDTO(bill));

        BigDecimal billCollectedAmount = this.billService.getCollectedAmount(bill.getId());
        BigDecimal collectionCollectedAmount = this.collectionService.getCollectedAmountByBillId(bill.getId());
        BigDecimal collectedAmount = billCollectedAmount.add(collectionCollectedAmount);

        uncollectedBillDTO.setTotalAmount(bill.getTotalAmount());
        uncollectedBillDTO.setCollectedAmount(collectedAmount);
        uncollectedBillDTO.setAmountToCollect(bill.getTotalAmount().subtract(collectedAmount));

        return uncollectedBillDTO;
    }

}
