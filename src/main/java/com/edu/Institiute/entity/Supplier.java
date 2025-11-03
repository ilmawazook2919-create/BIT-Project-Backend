package com.edu.Institiute.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "supplier")
public class Supplier {
    @Id
    @Column(name="id")
    private int id;

    @Column(name="supplierName")
    private String supplierName;

    @Column(name="supplierContact")
    private String supplierContact;

    @Column(name="supplierEmail")
    private String supplierEmail;

    @Column(name="supplierAddress")
    private String supplierAddress;

    @Column(name="supplierContactPerson")
    private String supplierContactPerson;

    @Column(name="supplierCreatedBy")
    private String supplierCreatedBy;

    @Column(name="supplierCreatedDate")
    private Date supplierCreatedDate;

    @Column(name="supplierModifyBy")
    private String supplierModifyBy;

    @Column(name="supplierModifyDate")
    private Date supplierModifyDate;


    @ManyToOne
    @JoinColumn(name="status_id", referencedColumnName = "id")
    private Status status;
}

