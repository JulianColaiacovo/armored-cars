package com.jcolaiacovo.armored.cars.domain.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Created by Julian on 09/01/2017.
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "BILLING_AND_REFERENCE")
public class BillingAndReference {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private int id;

    @Column(name = "OWNER", length = 100)
    private String owner;

    @Column(name = "CONTACT_PERSON", length = 100)
    private String contactPerson;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "BILL_TO_CLIENT_ID", nullable = false)
    private Client billToClient;

    public BillingAndReference() {
    }

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

    public Client getBillToClient() {
        return billToClient;
    }

    public void setBillToClient(Client billToClient) {
        this.billToClient = billToClient;
    }

}
