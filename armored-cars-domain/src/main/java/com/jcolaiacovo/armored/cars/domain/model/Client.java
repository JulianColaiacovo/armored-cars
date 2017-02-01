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
@Table(name = "CLIENT")
public class Client {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private int id;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "DOCUMENT", length = 100)
    private String document;

    @Column(name = "DOCUMENT_TYPE", length = 10, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private DocumentType documentType;

    @Column(name = "EMAIL", length = 10)
    private String email;

    @Column(name = "PHONE", length = 10)
    private String phone;

    public Client() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
