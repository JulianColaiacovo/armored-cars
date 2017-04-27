package com.jcolaiacovo.armored.cars.domain.service;

import com.google.common.collect.Lists;
import com.jcolaiacovo.armored.cars.domain.model.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Julian on 26/04/2017.
 */
@Component
public class ArmoredExcelHelper {

    private final ClientService clientService;

    @Autowired
    public ArmoredExcelHelper(ClientService clientService) {
        this.clientService = clientService;
    }

    public List<Armored> parse(Workbook excel) {
        Sheet sheet = excel.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.rowIterator();
        rowIterator.next();
        rowIterator.next();
        rowIterator.next();

        List<Armored> armoreds = Lists.newArrayList();
        rowIterator.forEachRemaining(row -> armoreds.add(this.parse(row)));

        return armoreds.stream().filter(this::isNotEmpty).collect(Collectors.toList());
    }

    public boolean isNotEmpty(Armored armored) {
        return !this.isEmpty(armored);
    }

    public boolean isEmpty(Armored armored) {
        return armored.getDeliveryDate() == null && armored.getDepartureDate() == null &&
                armored.getEntryDate() == null && armored.getPrice().compareTo(BigDecimal.ZERO) == 0 &&
                StringUtils.isBlank(armored.getCar().getBrand()) &&
                StringUtils.isBlank(armored.getCar().getModel()) &&
                StringUtils.isBlank(armored.getCar().getChassisNumber()) &&
                StringUtils.isBlank(armored.getCar().getMotorNumber()) &&
                StringUtils.isBlank(armored.getCar().getDomain()) &&
                StringUtils.isBlank(armored.getBillingAndReference().getOwner()) &&
                StringUtils.isBlank(armored.getBillingAndReference().getContactPerson()) &&
                this.clientService.isGenericClient(armored.getBillingAndReference().getBillToClient());
    }

    private Armored parse(Row row) {
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

    private void setCode(Armored armored, Row row) {
        int code = (int) row.getCell(0).getNumericCellValue();
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

        String brand = this.getStringValue(row.getCell(6));
        String model = this.getStringValue(row.getCell(7));
        String chassis = this.getStringValue(row.getCell(8));
        String motor = this.getStringValue(row.getCell(9));
        String domain = this.getStringValue(row.getCell(10));

        car.setBrand(brand);
        car.setModel(model);
        car.setChassisNumber(chassis);
        car.setMotorNumber(motor);
        car.setDomain(domain);

        armored.setCar(car);
    }

    private void setStockStatus(Armored armored, Row row) {
        String stockStatus = this.getStringValue(row.getCell(11));
        String stockStatusNormalized = StringUtils.stripAccents(stockStatus);

        if ("si".equalsIgnoreCase(stockStatusNormalized)) {
            armored.setStockStatus(StockStatus.WITH_STOCK);
        } else {
            armored.setStockStatus(StockStatus.WITHOUT_STOCK);
        }
    }

    private void setBillingAndReference(Armored armored, Row row) {
        BillingAndReference billingAndReference = new BillingAndReference();

        String owner = this.getStringValue(row.getCell(13));
        String contactPerson = this.getStringValue(row.getCell(14));
        String billTo = this.getStringValue(row.getCell(15));

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

    private String getStringValue(Cell cell) {
        return Optional.ofNullable(cell).map(Cell::getStringCellValue).orElse(null);
    }

}
