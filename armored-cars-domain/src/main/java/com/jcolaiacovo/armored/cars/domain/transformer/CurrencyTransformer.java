package com.jcolaiacovo.armored.cars.domain.transformer;

import com.jcolaiacovo.armored.cars.api.model.CurrencyDTO;
import com.jcolaiacovo.armored.cars.domain.model.Currency;
import org.springframework.stereotype.Component;

/**
 * Created by Julian on 10/04/2017.
 */
@Component
public class CurrencyTransformer extends AbstractApiTransformer<Currency, CurrencyDTO> {

    @Override
    public Currency transform(CurrencyDTO currencyDTO) {
        Currency currency = new Currency();

        currency.setId(currencyDTO.getId());
        currency.setCode(currencyDTO.getCode());
        currency.setDescription(currencyDTO.getDescription());

        return currency;
    }

    @Override
    public CurrencyDTO transformToDTO(Currency currency) {
        CurrencyDTO currencyDTO = new CurrencyDTO();

        currencyDTO.setId(currency.getId());
        currencyDTO.setCode(currency.getCode());
        currencyDTO.setDescription(currency.getDescription());

        return currencyDTO;
    }

}
