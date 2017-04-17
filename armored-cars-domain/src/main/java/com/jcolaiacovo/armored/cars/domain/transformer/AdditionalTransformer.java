package com.jcolaiacovo.armored.cars.domain.transformer;

import com.jcolaiacovo.armored.cars.api.model.AdditionalDTO;
import com.jcolaiacovo.armored.cars.domain.model.Additional;
import com.jcolaiacovo.armored.cars.domain.model.Armored;
import com.jcolaiacovo.armored.cars.domain.model.Currency;
import com.jcolaiacovo.armored.cars.domain.service.ArmoredService;
import com.jcolaiacovo.armored.cars.domain.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Julian on 10/04/2017.
 */
@Component
public class AdditionalTransformer extends AbstractApiTransformer<Additional, AdditionalDTO> {

    private ArmoredService armoredService;
    private CurrencyService currencyService;

    @Autowired
    public AdditionalTransformer(ArmoredService armoredService,
                                 CurrencyService currencyService) {
        this.armoredService = armoredService;
        this.currencyService = currencyService;
    }

    @Override
    public Additional transform(AdditionalDTO additionalDTO) {
        Additional additional = new Additional();

        additional.setId(additionalDTO.getId());
        additional.setDate(additionalDTO.getDate());
        additional.setAmount(additionalDTO.getAmount());
        Armored armored = this.armoredService.getById(additionalDTO.getArmoredId());
        additional.setArmored(armored);
        Currency currency = this.currencyService.getByCode(additionalDTO.getCurrencyCode());
        additional.setCurrency(currency);
        additional.setConversion(additionalDTO.getConversion());
        additional.setDescription(additionalDTO.getDescription());

        return additional;
    }

    @Override
    public AdditionalDTO transformToDTO(Additional additional) {
        AdditionalDTO additionalDTO = new AdditionalDTO();

        additionalDTO.setId(additional.getId());
        additionalDTO.setDate(additional.getDate());
        additionalDTO.setAmount(additional.getAmount());
        additionalDTO.setArmoredId(additional.getArmored().getId());
        additionalDTO.setCurrencyCode(additional.getCurrency().getCode());
        additionalDTO.setConversion(additional.getConversion());
        additionalDTO.setDescription(additional.getDescription());

        return additionalDTO;
    }

}
