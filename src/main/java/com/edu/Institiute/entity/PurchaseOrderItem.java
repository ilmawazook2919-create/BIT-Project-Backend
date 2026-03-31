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
@Table(name=" purchase_Order_Item")

public class PurchaseOrderItem {

    @Id
    @Column(name="id")
    private int id;

    @Column(name = "quantity_ordered")
    private int quantityOrdered;

    @Column(name = "unit_cost")
    private BigDecimal unitCost;

    @Column(name = "created_by")
    private int createdBy;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "modify_by")
    private int modifyBy;

    @Column(name = "modify_date")
    private Date modifyDate;

    @ManyToOne
    @JoinColumn(name="part_id", referencedColumnName = "id")
    private Part part;

    @ManyToOne
    @JoinColumn(name="purchase_Order_Id", referencedColumnName = "id")
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

}







