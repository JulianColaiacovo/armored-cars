package com.jcolaiacovo.armored.cars.domain.helper;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by Julian on 27/05/2017.
 */
@Component
public class ExcelHelper {

    public String getStringValue(Cell cell) {
        return Optional.ofNullable(cell).map(Cell::getStringCellValue).orElse(null);
    }

    public int getIntegerValue(Cell cell) {
        return Optional.ofNullable(cell)
                .filter(this::isNumericCell)
                .map(Cell::getNumericCellValue)
                .map(Double::intValue)
                .orElse(0);
    }

    private boolean isNumericCell(Cell cell) {
        if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            return true;
        } else if (cell.getCellTypeEnum() == CellType.FORMULA && cell.getCachedFormulaResultTypeEnum() == CellType.NUMERIC) {
            return true;
        }
        return false;
    }

}
