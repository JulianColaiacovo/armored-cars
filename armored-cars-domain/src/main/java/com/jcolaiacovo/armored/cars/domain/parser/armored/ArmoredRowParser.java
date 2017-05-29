package com.jcolaiacovo.armored.cars.domain.parser.armored;

import com.jcolaiacovo.armored.cars.domain.helper.ExcelHelper;
import com.jcolaiacovo.armored.cars.domain.model.*;
import com.jcolaiacovo.armored.cars.domain.parser.RowParser;
import com.jcolaiacovo.armored.cars.domain.parser.armored.exception.ArmoredWithoutBrandException;
import com.jcolaiacovo.armored.cars.domain.parser.armored.exception.ArmoredWithoutCodeException;
import com.jcolaiacovo.armored.cars.domain.service.ClientService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Julian on 27/05/2017.
 */
@Component
public class ArmoredRowParser extends RowParser<Armored> {

    private ClientService clientService;
    private ExcelHelper excelHelper;

    @Autowired
    public ArmoredRowParser(ClientService clientService,
                            ExcelHelper excelHelper) {
        this.clientService = clientService;
        this.excelHelper = excelHelper;
    }

    @Override
    public Armored parse(Row row) {
        Armored armored = new Armored();

        this.setCode(armored, row);
        this.setEntryDate(armored, row);
        this.setDeliveryDate(armored, row);
        this.setDepartureDate(armored, row);
        this.setCar(armored, row);
        this.setStockStatus(armored, row);
        this.setBillingAndReference(armored, row);
        this.setPrice(armored, row);

        return armored;
    }

    @Override
    public boolean isEmpty(Row row) {
        return StringUtils.isBlank(this.getBrand(row)) || this.getCode(row) == 0;
    }

    private int getCode(Row row) {
        return this.excelHelper.getIntegerValue(row.getCell(0));
    }

    private void setCode(Armored armored, Row row) {
        int code = this.getCode(row);
        if (code == 0) {
            throw new ArmoredWithoutCodeException();
        }
        armored.setCode(code);
    }

    private void setEntryDate(Armored armored, Row row) {
        Date entryDate = row.getCell(2).getDateCellValue();
        if (entryDate == null) {
            armored.setEntryDate(null);
        } else {
            armored.setEntryDate(new DateTime(entryDate));
        }
    }

    private void setDeliveryDate(Armored armored, Row row) {
        Date deliveryDate = row.getCell(3).getDateCellValue();
        if (deliveryDate == null) {
            armored.setDeliveryDate(null);
        } else {
            armored.setDeliveryDate(new DateTime(deliveryDate));
        }
    }

    private void setDepartureDate(Armored armored, Row row) {
        Date departureDate = row.getCell(4).getDateCellValue();
        if (departureDate == null) {
            armored.setDepartureDate(null);
        } else {
            armored.setDepartureDate(new DateTime(departureDate));
        }
    }

    private void setCar(Armored armored, Row row) {
        Car car = new Car();

        String brand = this.getBrand(row);
        if (StringUtils.isBlank(brand)) {
            throw new ArmoredWithoutBrandException();
        }
        String model = this.excelHelper.getStringValue(row.getCell(7));
        String chassis = this.excelHelper.getStringValue(row.getCell(8));
        String motor = this.excelHelper.getStringValue(row.getCell(9));
        String domain = this.excelHelper.getStringValue(row.getCell(10));

        car.setBrand(brand);
        car.setModel(model);
        car.setChassisNumber(chassis);
        car.setMotorNumber(motor);
        car.setDomain(domain);

        armored.setCar(car);
    }

    private String getBrand(Row row) {
        return this.excelHelper.getStringValue(row.getCell(6));
    }

    private void setStockStatus(Armored armored, Row row) {
        String stockStatus = this.excelHelper.getStringValue(row.getCell(11));
        String stockStatusNormalized = StringUtils.stripAccents(stockStatus);

        if ("si".equalsIgnoreCase(stockStatusNormalized)) {
            armored.setStockStatus(StockStatus.WITH_STOCK);
        } else {
            armored.setStockStatus(StockStatus.WITHOUT_STOCK);
        }
    }

    private void setBillingAndReference(Armored armored, Row row) {
        BillingAndReference billingAndReference = new BillingAndReference();

        String owner = this.excelHelper.getStringValue(row.getCell(13));
        String contactPerson = this.excelHelper.getStringValue(row.getCell(14));
        String billTo = this.excelHelper.getStringValue(row.getCell(15));

        billingAndReference.setOwner(owner);
        billingAndReference.setContactPerson(contactPerson);

        Client client = this.clientService.findByName(billTo).orElseGet(this.clientService::getGenericClient);
        billingAndReference.setBillToClient(client);

        armored.setBillingAndReference(billingAndReference);
    }

    private void setPrice(Armored armored, Row row) {
        double price = row.getCell(16).getNumericCellValue();
        armored.setPrice(BigDecimal.valueOf(price));
    }

}
