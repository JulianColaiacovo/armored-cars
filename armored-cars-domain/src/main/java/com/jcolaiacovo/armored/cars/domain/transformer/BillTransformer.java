package com.jcolaiacovo.armored.cars.domain.transformer;

import com.jcolaiacovo.armored.cars.api.model.BillDTO;
import com.jcolaiacovo.armored.cars.domain.model.Bill;
import com.jcolaiacovo.armored.cars.domain.model.BillTypeCode;
import com.jcolaiacovo.armored.cars.domain.service.ArmoredService;
import com.jcolaiacovo.armored.cars.domain.service.BillService;
import com.jcolaiacovo.armored.cars.domain.service.ClientService;
import com.jcolaiacovo.armored.cars.domain.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by Julian on 10/04/2017.
 */
@Component
public class BillTransformer extends AbstractApiTransformer<Bill, BillDTO> {

    private ArmoredService armoredService;
    private BillService billService;
    private ClientService clientService;
    private CurrencyService currencyService;

    @Autowired
    public BillTransformer(ArmoredService armoredService,
                           CurrencyService currencyService,
                           BillService billService,
                           ClientService clientService) {
        this.armoredService = armoredService;
        this.currencyService = currencyService;
        this.billService = billService;
        this.clientService = clientService;
    }

    @Override
    public Bill transform(BillDTO billDTO) {
        Bill bill = new Bill();

        bill.setId(billDTO.getId());
        bill.setAliquot(billDTO.getAliquot());
        if (Optional.ofNullable(billDTO.getApplyBillId()).filter(integer -> integer > 0).isPresent()) {
            bill.setApplyBill(this.billService.getById(billDTO.getApplyBillId()));
        }
        bill.setArmored(this.armoredService.getById(billDTO.getArmoredId()));
        bill.setBillTo(this.clientService.getById(billDTO.getBillToId()));
        bill.setBillTypeCode(BillTypeCode.valueOf(billDTO.getBillTypeCode()));
        bill.setConversion(billDTO.getConversion());
        bill.setCurrency(this.currencyService.getByCode(billDTO.getCurrencyCode()));
        bill.setDate(billDTO.getDate());
        bill.setDescription(billDTO.getDescription());
        bill.setFinancialAdvance(billDTO.getFinancialAdvance());
        bill.setNumber(billDTO.getNumber());
        bill.setTaxedAmount(billDTO.getTaxedAmount());
        bill.setTotalAmount(billDTO.getTotalAmount());
        bill.setUntaxedAmount(billDTO.getUntaxedAmount());
        bill.setVatAmount(billDTO.getVatAmount());

        return bill;
    }

    @Override
    public BillDTO transformToDTO(Bill bill) {
        BillDTO billDTO = new BillDTO();

        billDTO.setId(bill.getId());
        billDTO.setAliquot(bill.getAliquot());
        billDTO.setApplyBillId(Optional.ofNullable(bill.getApplyBill()).map(Bill::getId).orElse(0));
        billDTO.setArmoredId(bill.getArmored().getId());
        billDTO.setBillToId(bill.getBillTo().getId());
        billDTO.setBillTypeCode(bill.getBillTypeCode().name());
        billDTO.setConversion(bill.getConversion());
        billDTO.setCurrencyCode(bill.getCurrency().getCode());
        billDTO.setDate(bill.getDate());
        billDTO.setDescription(bill.getDescription());
        billDTO.setFinancialAdvance(bill.getFinancialAdvance());
        billDTO.setNumber(bill.getNumber());
        billDTO.setTaxedAmount(bill.getTaxedAmount());
        billDTO.setTotalAmount(bill.getTotalAmount());
        billDTO.setUntaxedAmount(bill.getUntaxedAmount());
        billDTO.setVatAmount(bill.getVatAmount());

        return billDTO;
    }

}
