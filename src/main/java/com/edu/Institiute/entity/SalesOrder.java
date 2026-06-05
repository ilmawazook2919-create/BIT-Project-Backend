package com.edu.Institiute.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name="salesOrder")
public class SalesOrder {
    @Id
    @Column(name="id")
    private int id;

    @Column(name = "orderNumber")
    private String orderNumber;

    @Column(name = "orderDate")
    private Date orderDate;

    @Column(name = "requiredDate")
    private Date requiredDate;

    @Column(name = "totalAmount")
    private BigDecimal totalAmount;

    @Column(name = "createdBy")
    private int createdBy;

    @Column(name = "createdDate")
    private Date createdDate;

    @Column(name = "modifyBy")
    private int modifyBy;

    @Column(name = "modifyDate")
    private Date modifyDate;

    @ManyToOne
    @JoinColumn(name="customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="warehouse_id", referencedColumnName = "id")
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

}
