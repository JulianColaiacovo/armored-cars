package com.jcolaiacovo.armored.cars.domain.transformer;

import com.jcolaiacovo.armored.cars.api.model.ArmoredDTO;
import com.jcolaiacovo.armored.cars.domain.model.Armored;
import com.jcolaiacovo.armored.cars.domain.model.StockStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Julian on 10/04/2017.
 */
@Component
public class ArmoredTransformer extends AbstractApiTransformer<Armored, ArmoredDTO> {

    private CarTransformer carTransformer;
    private BillingAndReferenceTransformer billingAndReferenceTransformer;

    @Autowired
    public ArmoredTransformer(CarTransformer carTransformer,
                              BillingAndReferenceTransformer billingAndReferenceTransformer) {
        this.carTransformer = carTransformer;
        this.billingAndReferenceTransformer = billingAndReferenceTransformer;
    }

    @Override
    public Armored transform(ArmoredDTO armoredDTO) {
        Armored armored = new Armored();

        armored.setId(armoredDTO.getId());
        armored.setBillingAndReference(this.billingAndReferenceTransformer.transform(armoredDTO.getBillingAndReference()));
        armored.setCar(this.carTransformer.transform(armoredDTO.getCar()));
        armored.setCode(armoredDTO.getCode());
        armored.setDeliveryDate(armoredDTO.getDeliveryDate());
        armored.setDepartureDate(armoredDTO.getDepartureDate());
        armored.setEntryDate(armoredDTO.getEntryDate());
        armored.setPrice(armoredDTO.getPrice());
        armored.setStockStatus(StockStatus.valueOf(armoredDTO.getStockStatus()));

        return armored;
    }

    @Override
    public ArmoredDTO transformToDTO(Armored armored) {
        ArmoredDTO armoredDTO = new ArmoredDTO();

        armoredDTO.setId(armored.getId());
        armoredDTO.setCode(armored.getCode());
        armoredDTO.setEntryDate(armored.getEntryDate());
        armoredDTO.setDeliveryDate(armored.getDeliveryDate());
        armoredDTO.setDepartureDate(armored.getDepartureDate());
        armoredDTO.setPrice(armored.getPrice());
        armoredDTO.setStockStatus(armored.getStockStatus().name());
        armoredDTO.setCar(this.carTransformer.transformToDTO(armored.getCar()));
        armoredDTO.setBillingAndReference(this.billingAndReferenceTransformer.transformToDTO(armored.getBillingAndReference()));

        return armoredDTO;
    }

}
