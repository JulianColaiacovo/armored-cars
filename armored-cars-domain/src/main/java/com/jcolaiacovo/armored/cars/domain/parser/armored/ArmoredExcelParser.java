package com.jcolaiacovo.armored.cars.domain.parser.armored;

import com.google.common.collect.Lists;
import com.jcolaiacovo.armored.cars.domain.model.Armored;
import com.jcolaiacovo.armored.cars.domain.parser.armored.exception.ArmoredExcelEmptyException;
import com.jcolaiacovo.armored.cars.domain.parser.armored.exception.ArmoredExcelWithoutHeadersException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Julian on 27/05/2017.
 */
@Component
public class ArmoredExcelParser {

    private static final int HEADERS_COUNT = 3;

    private ArmoredRowParser armoredRowParser;

    @Autowired
    public ArmoredExcelParser(ArmoredRowParser armoredRowParser) {
        this.armoredRowParser = armoredRowParser;
    }

    public List<Armored> parse(Workbook excel) {
        Sheet sheet = excel.getSheetAt(0);

        ArrayList<Row> rows = Lists.newArrayList(sheet.rowIterator());

        if (rows.size() < HEADERS_COUNT) {
            throw new ArmoredExcelWithoutHeadersException();
        } else if (rows.size() == HEADERS_COUNT) {
            throw new ArmoredExcelEmptyException();
        }

        return rows.stream()
                .skip(HEADERS_COUNT)
                .filter(this.armoredRowParser::isNotEmpty)
                .map(this.armoredRowParser::parse)
                .collect(Collectors.toList());
    }

}
