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
@Table(name="bin")
public class Bin {

    @Id
    @Column(name="id")
    private String id;

    @Column(name ="binCode")
    private String binCode;

    @Column(name = "binDescription")
    private String binDescription;

    @Column(name = "binCreatedBy")
    private String binCreatedBy;

    @Column(name = "binCreatedDate")
    private Date binCreatedDate;

    @Column(name = "binModifiedBy")
    private String binModifiedBy;

    @Column(name = "binModifiedDate")
    private Date binModifiedDate;

    @ManyToOne
    @JoinColumn(name="status_id", referencedColumnName = "id")
    private Status status;
}
