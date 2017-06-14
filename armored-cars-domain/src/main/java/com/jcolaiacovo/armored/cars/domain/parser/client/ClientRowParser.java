package com.jcolaiacovo.armored.cars.domain.parser.client;

import com.jcolaiacovo.armored.cars.domain.helper.ExcelHelper;
import com.jcolaiacovo.armored.cars.domain.model.Client;
import com.jcolaiacovo.armored.cars.domain.model.DocumentType;
import com.jcolaiacovo.armored.cars.domain.parser.RowParser;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Julian on 27/05/2017.
 */
@Component
public class ClientRowParser extends RowParser<Client> {

    private ExcelHelper excelHelper;

    @Autowired
    public ClientRowParser(ExcelHelper excelHelper) {
        this.excelHelper = excelHelper;
    }

    @Override
    public Client parse(Row row) {
        Client client = new Client();

        this.setName(client, row);
        this.setDocumentType(client, row);
        this.setDocument(client, row);
        this.setEmail(client, row);
        this.setPhone(client, row);

        return client;
    }

    @Override
    public boolean isEmpty(Row row) {
        return StringUtils.isBlank(this.getName(row)) && StringUtils.isBlank(this.getDocument(row));
    }

    private String getName(Row row) {
        return this.excelHelper.getStringValue(row.getCell(0));
    }

    private void setName(Client client, Row row) {
        String name = this.getName(row);
        client.setName(name);
    }

    private DocumentType getDocumentType(Row row) {
        String documentType = this.excelHelper.getStringValue(row.getCell(1));
        return DocumentType.valueOf(documentType);
    }

    private void setDocumentType(Client client, Row row) {
        DocumentType documentType = this.getDocumentType(row);
        client.setDocumentType(documentType);
    }

    private String getDocument(Row row) {
        return this.excelHelper.getLongStringValue(row.getCell(2));
    }

    private void setDocument(Client client, Row row) {
        String document = this.getDocument(row);
        client.setDocument(document);
    }

    private void setEmail(Client client, Row row) {
        String email = this.excelHelper.getStringValue(row.getCell(3));
        client.setEmail(email);
    }

    private void setPhone(Client client, Row row) {
        String phone = this.excelHelper.getLongStringValue(row.getCell(4));
        client.setPhone(phone);
    }

}
