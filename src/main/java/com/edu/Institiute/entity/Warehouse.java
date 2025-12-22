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
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "locationName")
    private String locationName;

    @Column(name = "address")
    private String address;

    @Column(name = "isActive")
    private Boolean isActive;

    @Column(name = "warehouseCreatedBy")
    private String warehouseCreatedBy;

    @Column(name = "warehouseCreatedDate")
    private Date warehouseCreatedDate;

    @Column(name = "warehouseModifiedBy")
    private String warehouseModifiedBy;

    @Column(name = "warehouseModifiedDate")
    private Date warehouseModifiedDate;

    @ManyToOne
    @JoinColumn(name="status_id", referencedColumnName = "id")
    private Status status;


}
