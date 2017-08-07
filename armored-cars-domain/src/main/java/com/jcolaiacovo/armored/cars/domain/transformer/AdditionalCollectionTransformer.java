package com.jcolaiacovo.armored.cars.domain.transformer;

import com.jcolaiacovo.armored.cars.api.model.AdditionalCollectionDTO;
import com.jcolaiacovo.armored.cars.domain.model.Additional;
import com.jcolaiacovo.armored.cars.domain.model.AdditionalCollection;
import com.jcolaiacovo.armored.cars.domain.service.AdditionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Julian on 10/04/2017.
 */
@Component
public class AdditionalCollectionTransformer extends AbstractApiTransformer<AdditionalCollection, AdditionalCollectionDTO> {

    private AdditionalService additionalService;

    @Autowired
    public AdditionalCollectionTransformer(AdditionalService additionalService) {
        this.additionalService = additionalService;
    }

    @Override
    public AdditionalCollection transform(AdditionalCollectionDTO additionalCollectionDTO) {
        AdditionalCollection additionalCollection = new AdditionalCollection();

        additionalCollection.setId(additionalCollectionDTO.getId());
        additionalCollection.setDate(additionalCollectionDTO.getDate());
        Additional additional = this.additionalService.getById(additionalCollectionDTO.getAdditionalId());
        additionalCollection.setAdditional(additional);
        additionalCollection.setAmount(additionalCollectionDTO.getAmount());
        additionalCollection.setDescription(additionalCollectionDTO.getDescription());

        return additionalCollection;
    }

    @Override
    public AdditionalCollectionDTO transformToDTO(AdditionalCollection additionalCollection) {
        AdditionalCollectionDTO additionalCollectionDTO = new AdditionalCollectionDTO();

        additionalCollectionDTO.setId(additionalCollection.getId());
        additionalCollectionDTO.setDate(additionalCollection.getDate());
        additionalCollectionDTO.setAdditionalId(additionalCollection.getAdditional().getId());
        additionalCollectionDTO.setCurrencyCode(additionalCollection.getAdditional().getCurrency().getCode());
        additionalCollectionDTO.setAmount(additionalCollection.getAmount());
        additionalCollectionDTO.setDescription(additionalCollection.getDescription());

        return additionalCollectionDTO;
    }

}
