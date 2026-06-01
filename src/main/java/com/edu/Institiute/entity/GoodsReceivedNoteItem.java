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
@Table(name = "goodsReceivedNoteItem")
public class GoodsReceivedNoteItem {
    @Id
    @Column(name="id")
    private String id;

    @Column(name = "quantityReceived")
    private String quantityReceived;

    @Column(name = "createdBy")
    private int createdBy;

    @Column(name = "createdDate")
    private Date createdDate;

    @Column(name = "modifyBy")
    private int modifyBy;

    @Column(name = "modifyDate")
    private Date modifyDate;

    @ManyToOne
    @JoinColumn(name="goods_received_note_id", referencedColumnName = "id")
    private GoodsReceivedNote goodsReceivedNote;

    @ManyToOne
    @JoinColumn(name="part_id", referencedColumnName = "id")
    private Part part;

    @ManyToOne
    @JoinColumn(name="bin_id", referencedColumnName = "id")
    private Bin bin;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

}
