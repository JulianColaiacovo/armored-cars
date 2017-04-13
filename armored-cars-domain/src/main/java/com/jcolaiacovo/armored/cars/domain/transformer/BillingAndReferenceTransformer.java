package com.jcolaiacovo.armored.cars.domain.transformer;

import com.jcolaiacovo.armored.cars.api.model.BillingAndReferenceDTO;
import com.jcolaiacovo.armored.cars.domain.model.BillingAndReference;
import com.jcolaiacovo.armored.cars.domain.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Julian on 10/04/2017.
 */
@Component
public class BillingAndReferenceTransformer extends AbstractApiTransformer<BillingAndReference, BillingAndReferenceDTO> {

    private ClientService clientService;

    @Autowired
    public BillingAndReferenceTransformer(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public BillingAndReference transform(BillingAndReferenceDTO billingAndReferenceDTO) {
        BillingAndReference billingAndReference = new BillingAndReference();

        billingAndReference.setId(billingAndReferenceDTO.getId());
        billingAndReference.setBillToClient(this.clientService.getById(billingAndReferenceDTO.getBillToClientId()));
        billingAndReference.setContactPerson(billingAndReferenceDTO.getContactPerson());
        billingAndReference.setOwner(billingAndReferenceDTO.getOwner());

        return billingAndReference;
    }

    @Override
    public BillingAndReferenceDTO transformToDTO(BillingAndReference billingAndReference) {
        BillingAndReferenceDTO billingAndReferenceDTO = new BillingAndReferenceDTO();

        billingAndReferenceDTO.setId(billingAndReference.getId());
        billingAndReferenceDTO.setOwner(billingAndReference.getOwner());
        billingAndReferenceDTO.setBillToClientId(billingAndReference.getBillToClient().getId());
        billingAndReferenceDTO.setContactPerson(billingAndReference.getContactPerson());

        return billingAndReferenceDTO;
    }

}
