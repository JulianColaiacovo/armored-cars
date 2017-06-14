package com.jcolaiacovo.armored.cars.domain.helper;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

/**
 * Created by Julian on 27/05/2017.
 */
@Component
public class ExcelHelper {

    private static final Set<CellType> EMPTY_CELL_TYPES = Sets.newHashSet(CellType.BLANK, CellType._NONE, CellType.ERROR);

    public String getStringValue(Cell cell) {
        return Optional.ofNullable(cell).map(Cell::toString).orElse(null);
    }

    public String getLongStringValue(Cell cell) {
        return Long.toString(this.getLongValue(cell));
    }

    public long getLongValue(Cell cell) {
        return Optional.ofNullable(cell)
                .filter(this::isNumericCell)
                .map(Cell::getNumericCellValue)
                .map(Double::longValue)
                .orElse(0L);
    }

    public int getIntegerValue(Cell cell) {
        return Optional.ofNullable(cell)
                .filter(this::isNumericCell)
                .map(Cell::getNumericCellValue)
                .map(Double::intValue)
                .orElse(0);
    }

    public boolean isNotEmptyRow(Row row) {
        return !this.isEmptyRow(row);
    }

    public boolean isEmptyRow(Row row) {
        return Lists.newArrayList(row.cellIterator()).stream().allMatch(this::isEmptyCell);
    }

    private boolean isNumericCell(Cell cell) {
        return cell.getCellTypeEnum() == CellType.NUMERIC || this.isNumericFormulaCell(cell);
    }

    private boolean isNumericFormulaCell(Cell cell) {
        return cell.getCellTypeEnum() == CellType.FORMULA && cell.getCachedFormulaResultTypeEnum() == CellType.NUMERIC;
    }

    public boolean isEmptyCell(Cell cell) {
        return EMPTY_CELL_TYPES.contains(cell.getCellTypeEnum());
    }

}
