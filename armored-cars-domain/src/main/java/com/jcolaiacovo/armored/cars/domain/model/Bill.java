package com.jcolaiacovo.armored.cars.domain.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Julian on 09/01/2017.
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "BILL")
public class Bill {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private int id;

    @Column(name = "DATE", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ARMORED_ID", nullable = false)
    private Armored armored;

    @Column(name = "CONVERSION", nullable = false)
    private BigDecimal conversion;

    @Column(name = "NUMBER", nullable = false)
    private Long number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CURRENCY_ID", nullable = false)
    private Currency currency;

    @Column(name = "BILL_TO_NAME", nullable = false)
    private String billToName;

    @Column(name = "BILL_TO_DOCUMENT_TYPE", nullable = false)
    private String billToDocumentType;

    @Column(name = "BILL_TO_DOCUMENT", nullable = false)
    private String billToDocument;

    @Column(name = "ALIQUOT", nullable = false)
    private BigDecimal aliquot;

    @Column(name = "TAXED_AMOUNT", nullable = false)
    private BigDecimal taxedAmount;

    @Column(name = "UNTAXED_AMOUNT", nullable = false)
    private BigDecimal untaxedAmount;

    @Column(name = "BILL_TYPE_CODE", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private BillTypeCode billTypeCode;

    @Column(name = "VAT_AMOUNT", nullable = false)
    private BigDecimal vatAmount;

    @Column(name = "TOTAL_AMOUNT", nullable = false)
    private BigDecimal totalAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "APPLY_BILL_ID")
    private Bill applyBill;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BILL_TO_ID", nullable = false)
    private Client billTo;

    @Column(name = "FINANCIAL_ADVANCE", nullable = false)
    private Boolean financialAdvance;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public Armored getArmored() {
        return armored;
    }

    public void setArmored(Armored armored) {
        this.armored = armored;
    }

    public BigDecimal getConversion() {
        return conversion;
    }

    public void setConversion(BigDecimal conversion) {
        this.conversion = conversion;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getBillToName() {
        return billToName;
    }

    public void setBillToName(String billToName) {
        this.billToName = billToName;
    }

    public String getBillToDocumentType() {
        return billToDocumentType;
    }

    public void setBillToDocumentType(String billToDocumentType) {
        this.billToDocumentType = billToDocumentType;
    }

    public String getBillToDocument() {
        return billToDocument;
    }

    public void setBillToDocument(String billToDocument) {
        this.billToDocument = billToDocument;
    }

    public BigDecimal getAliquot() {
        return aliquot;
    }

    public void setAliquot(BigDecimal aliquot) {
        this.aliquot = aliquot;
    }

    public BigDecimal getTaxedAmount() {
        return taxedAmount;
    }

    public void setTaxedAmount(BigDecimal taxedAmount) {
        this.taxedAmount = taxedAmount;
    }

    public BigDecimal getUntaxedAmount() {
        return untaxedAmount;
    }

    public void setUntaxedAmount(BigDecimal untaxedAmount) {
        this.untaxedAmount = untaxedAmount;
    }

    public BillTypeCode getBillTypeCode() {
        return billTypeCode;
    }

    public void setBillTypeCode(BillTypeCode billTypeCode) {
        this.billTypeCode = billTypeCode;
    }

    public BigDecimal getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(BigDecimal vatAmount) {
        this.vatAmount = vatAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Bill getApplyBill() {
        return applyBill;
    }

    public void setApplyBill(Bill applyBill) {
        this.applyBill = applyBill;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Client getBillTo() {
        return billTo;
    }

    public void setBillTo(Client billTo) {
        this.billTo = billTo;
    }

    public Boolean getFinancialAdvance() {
        return financialAdvance;
    }

    public void setFinancialAdvance(Boolean financialAdvance) {
        this.financialAdvance = financialAdvance;
    }

}
