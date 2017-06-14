package com.jcolaiacovo.armored.cars.domain.parser.client;

import com.google.common.collect.Lists;
import com.jcolaiacovo.armored.cars.domain.helper.ExcelHelper;
import com.jcolaiacovo.armored.cars.domain.parser.RowParser;
import com.jcolaiacovo.armored.cars.domain.parser.exception.ExcelEmptyException;
import com.jcolaiacovo.armored.cars.domain.parser.exception.ExcelWithoutHeadersException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Julian on 13/06/2017.
 */
public abstract class ExcelParser<T> {

    private ExcelHelper excelHelper;
    private RowParser<T> rowParser;

    public ExcelParser(ExcelHelper excelHelper, RowParser<T> rowParser) {
        this.excelHelper = excelHelper;
        this.rowParser = rowParser;
    }

    protected abstract int getHeadersCount();

    public List<T> parse(Workbook excel) {
        Sheet sheet = excel.getSheetAt(0);

        List<Row> rows = Lists.newArrayList(sheet.rowIterator());

        if (rows.size() < this.getHeadersCount()) {
            throw new ExcelWithoutHeadersException();
        } else if (rows.size() == this.getHeadersCount()) {
            throw new ExcelEmptyException();
        }

        return rows.stream()
                .skip(this.getHeadersCount())
                .filter(this.excelHelper::isNotEmptyRow)
                .filter(this.rowParser::isNotEmpty)
                .map(this.rowParser::parse)
                .collect(Collectors.toList());
    }

}
