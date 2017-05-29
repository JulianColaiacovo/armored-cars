package com.jcolaiacovo.armored.cars.domain.parser.armored;

import com.jcolaiacovo.armored.cars.domain.helper.ExcelHelper;
import com.jcolaiacovo.armored.cars.domain.parser.armored.exception.ArmoredWithoutCodeException;
import com.jcolaiacovo.armored.cars.domain.service.ClientService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by Julian on 27/05/2017.
 */
@Test
public class ArmoredRowParserTest {

    private ArmoredRowParser armoredRowParser;

    @Mock
    private ClientService clientService;

    @Mock
    private ExcelHelper excelHelper;

    @BeforeTest
    private void initMocks() {
        MockitoAnnotations.initMocks(this);
        this.armoredRowParser = new ArmoredRowParser(this.clientService, this.excelHelper);
    }

    @Test(expectedExceptions = ArmoredWithoutCodeException.class)
    public void parseWithoutCode_error() {
        Row row = Mockito.mock(Row.class);

        Cell codeCell = Mockito.mock(Cell.class);
        Mockito.when(codeCell.getCellTypeEnum()).thenReturn(CellType.NUMERIC);
        Mockito.when(codeCell.getNumericCellValue()).thenReturn(0D);

        Mockito.when(row.getCell(Mockito.eq(0))).thenReturn(codeCell);

        this.armoredRowParser.parse(row);
    }

}
