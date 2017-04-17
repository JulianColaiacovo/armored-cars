package com.jcolaiacovo.armored.cars.domain.transformer;

import com.jcolaiacovo.armored.cars.api.model.CollectionDTO;
import com.jcolaiacovo.armored.cars.domain.model.Bill;
import com.jcolaiacovo.armored.cars.domain.model.Collection;
import com.jcolaiacovo.armored.cars.domain.service.BillService;
import com.jcolaiacovo.armored.cars.domain.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by Julian on 11/04/2017.
 */
@Component
public class CollectionTransformer extends AbstractApiTransformer<Collection, CollectionDTO> {

    private final BillService billService;
    private final ClientService clientService;

    @Autowired
    public CollectionTransformer(BillService billService, ClientService clientService) {
        this.billService = billService;
        this.clientService = clientService;
    }

    @Override
    public Collection transform(CollectionDTO collectionDTO) {
        Collection collection = new Collection();

        collection.setId(collectionDTO.getId());
        collection.setVatAmount(collectionDTO.getVatAmount());
        collection.setBaseAmount(collectionDTO.getBaseAmount());
        collection.setDate(collectionDTO.getDate());
        collection.setDescription(collectionDTO.getDescription());
        collection.setGainAmount(collectionDTO.getGainAmount());
        collection.setIibbAmount(collectionDTO.getIibbAmount());
        collection.setSussAmount(collectionDTO.getSussAmount());
        collection.setTotalAmount(collectionDTO.getTotalAmount());
        if (Optional.ofNullable(collectionDTO.getBillId()).filter(integer -> integer > 0).isPresent()) {
            collection.setBill(this.billService.getById(collectionDTO.getBillId()));
        }
        collection.setClient(this.clientService.getById(collectionDTO.getClientId()));

        return collection;
    }

    @Override
    public CollectionDTO transformToDTO(Collection collection) {
        CollectionDTO collectionDTO = new CollectionDTO();

        collectionDTO.setId(collection.getId());
        collectionDTO.setVatAmount(collection.getVatAmount());
        collectionDTO.setBaseAmount(collection.getBaseAmount());
        collectionDTO.setDate(collection.getDate());
        collectionDTO.setDescription(collection.getDescription());
        collectionDTO.setGainAmount(collection.getGainAmount());
        collectionDTO.setIibbAmount(collection.getIibbAmount());
        collectionDTO.setSussAmount(collection.getSussAmount());
        collectionDTO.setTotalAmount(collection.getTotalAmount());
        Optional<Integer> billId = Optional.ofNullable(collection.getBill()).map(Bill::getId);
        if (billId.isPresent()) {
            collectionDTO.setBillId(billId.get());
        }
        collectionDTO.setClientId(collection.getClient().getId());

        return collectionDTO;
    }

}
