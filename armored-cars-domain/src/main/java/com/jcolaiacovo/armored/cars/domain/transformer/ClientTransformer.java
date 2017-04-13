package com.jcolaiacovo.armored.cars.domain.transformer;

import com.jcolaiacovo.armored.cars.api.model.ClientDTO;
import com.jcolaiacovo.armored.cars.domain.model.Client;
import com.jcolaiacovo.armored.cars.domain.model.DocumentType;
import org.springframework.stereotype.Component;

/**
 * Created by Julian on 10/04/2017.
 */
@Component
public class ClientTransformer extends AbstractApiTransformer<Client, ClientDTO> {

    @Override
    public Client transform(ClientDTO clientDTO) {
        Client client = new Client();

        client.setId(clientDTO.getId());
        client.setDocument(clientDTO.getDocument());
        client.setDocumentType(DocumentType.valueOf(clientDTO.getDocumentType()));
        client.setEmail(clientDTO.getEmail());
        client.setName(clientDTO.getName());
        client.setPhone(clientDTO.getPhone());

        return client;
    }

    @Override
    public ClientDTO transformToDTO(Client client) {
        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setId(client.getId());
        clientDTO.setDocument(client.getDocument());
        clientDTO.setDocumentType(client.getDocumentType().name());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setName(client.getName());
        clientDTO.setPhone(client.getPhone());

        return clientDTO;
    }

}
