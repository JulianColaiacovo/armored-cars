package com.jcolaiacovo.armored.cars.domain.parser.client;

import com.jcolaiacovo.armored.cars.domain.helper.ExcelHelper;
import com.jcolaiacovo.armored.cars.domain.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Julian on 27/05/2017.
 */
@Component
public class ClientExcelParser extends ExcelParser<Client> {

    @Autowired
    public ClientExcelParser(ExcelHelper excelHelper,
                             ClientRowParser clientRowParser) {
        super(excelHelper, clientRowParser);
    }

    @Override
    protected int getHeadersCount() {
        return 1;
    }

}
