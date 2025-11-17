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
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name="id")
    private int id;

    @Column(name="customerFName")
    private String customerFName;

    @Column(name="customerLName")
    private String customerLName;

    @Column(name="customerContact")
    private String customerContact;

    @Column(name="customerEmail")
    private String customerEmail;

    @Column(name="customerAddress")
    private String customerAddress;

    @Column(name="customerCreatedBy")
    private String customerCreatedBy;

    @Column(name="customerCreatedDate")
    private Date customerCreatedDate;

    @Column(name="customerModifyBy")
    private String customerModifyBy;

    @Column(name="customerModifyDate")
    private Date customerModifyDate;

    @ManyToOne
    @JoinColumn(name="status_id", referencedColumnName = "id")
    private Status status;


}
