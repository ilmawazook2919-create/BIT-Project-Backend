package com.edu.Institiute.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name="inventoryLevel")
public class InventoryLevel {

    @Id
    @Column(name="id")
    private int id;

    @Column(name = "quantity_on_hand")
    private int quantityOnHand;

    @Column(name = "minimum_stock_level")
    private int minimumStockLevel;

    @Column(name = "maximum_stock_level")
    private int maximumStockLevel;

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
    @JoinColumn(name="bin_id", referencedColumnName = "id")
    private Bin bin;

    @ManyToOne
    @JoinColumn(name="status_id", referencedColumnName = "id")
    private Status status;



}
