package com.jcolaiacovo.armored.cars.domain.transformer;

import com.jcolaiacovo.armored.cars.api.model.BillingAndReferenceDTO;
import com.jcolaiacovo.armored.cars.domain.model.BillingAndReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Julian on 10/04/2017.
 */
@Component
public class BillingAndReferenceTransformer extends AbstractApiTransformer<BillingAndReference, BillingAndReferenceDTO> {

    private final ClientTransformer clientTransformer;

    @Autowired
    public BillingAndReferenceTransformer(ClientTransformer clientTransformer) {
        this.clientTransformer = clientTransformer;
    }

    @Override
    public BillingAndReference transform(BillingAndReferenceDTO billingAndReferenceDTO) {
        BillingAndReference billingAndReference = new BillingAndReference();

        billingAndReference.setId(billingAndReferenceDTO.getId());
        billingAndReference.setBillToClient(this.clientTransformer.transform(billingAndReferenceDTO.getBillToClient()));
        billingAndReference.setContactPerson(billingAndReferenceDTO.getContactPerson());
        billingAndReference.setOwner(billingAndReferenceDTO.getOwner());

        return billingAndReference;
    }

    @Override
    public BillingAndReferenceDTO transformToDTO(BillingAndReference billingAndReference) {
        BillingAndReferenceDTO billingAndReferenceDTO = new BillingAndReferenceDTO();

        billingAndReferenceDTO.setId(billingAndReference.getId());
        billingAndReferenceDTO.setOwner(billingAndReference.getOwner());
        billingAndReferenceDTO.setBillToClient(this.clientTransformer.transformToDTO(billingAndReference.getBillToClient()));
        billingAndReferenceDTO.setContactPerson(billingAndReference.getContactPerson());

        return billingAndReferenceDTO;
    }

}
