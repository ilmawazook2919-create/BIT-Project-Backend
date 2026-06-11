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
@Table(name="picking_list")
public class PickingList {
    @Id
    @Column(name="id")
    private int id;

    @Column(name ="shipmentDate" )
    private Date shipmentDate;

    @Column(name ="trackingNumber" )
    private int trackingNumber;

    @Column(name = "createdBy")
    private int createdBy;

    @Column(name = "createdDate")
    private Date createdDate;

    @Column(name = "modifyBy")
    private int modifyBy;

    @Column(name = "modifyDate")
    private Date modifyDate;

    @ManyToOne
    @JoinColumn(name="salesOrder_id", referencedColumnName = "id")
    private SalesOrder salesOrder;

    @ManyToOne
    @JoinColumn(name="shippedBy_id", referencedColumnName = "userName")
    private User user;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

}
