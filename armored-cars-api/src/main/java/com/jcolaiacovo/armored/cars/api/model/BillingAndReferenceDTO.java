package com.jcolaiacovo.armored.cars.api.model;

/**
 * Created by Julian on 10/04/2017.
 */
public class BillingAndReferenceDTO {

    private int id;
    private String owner;
    private String contactPerson;
    private int billToClientId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public int getBillToClientId() {
        return billToClientId;
    }

    public void setBillToClientId(int billToClientId) {
        this.billToClientId = billToClientId;
    }

}
