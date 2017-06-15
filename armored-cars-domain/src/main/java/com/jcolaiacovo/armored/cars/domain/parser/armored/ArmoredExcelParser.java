package com.jcolaiacovo.armored.cars.domain.parser.armored;

import com.jcolaiacovo.armored.cars.domain.helper.ExcelHelper;
import com.jcolaiacovo.armored.cars.domain.model.Armored;
import com.jcolaiacovo.armored.cars.domain.parser.RowParser;
import com.jcolaiacovo.armored.cars.domain.parser.ExcelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Julian on 27/05/2017.
 */
@Component
public class ArmoredExcelParser extends ExcelParser<Armored> {

    @Autowired
    public ArmoredExcelParser(ExcelHelper excelHelper, RowParser<Armored> rowParser) {
        super(excelHelper, rowParser);
    }

    @Override
    protected int getHeadersCount() {
        return 3;
    }

}
