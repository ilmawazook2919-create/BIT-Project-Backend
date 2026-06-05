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
@Table(name="salesOrderItem")
public class SalesOrderItem {
    @Id
    @Column(name="id")
    private int id;

    @Column(name = "quantity_ordered")
    private int quantityOrdered;

    @Column(name="unitCost", precision = 15, scale = 4)  // Added precision/scale
    private BigDecimal unitCost;

    @Column(name = "createdBy")
    private int createdBy;

    @Column(name = "createdDate")
    private Date createdDate;

    @Column(name = "modifyBy")
    private int modifyBy;

    @Column(name = "modifyDate")
    private Date modifyDate;

    @ManyToOne
    @JoinColumn(name="part_id", referencedColumnName = "id")
    private Part part;

    @ManyToOne
    @JoinColumn(name = "salesOrder_id", referencedColumnName = "id")
    private SalesOrder salesOrder;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;
}
