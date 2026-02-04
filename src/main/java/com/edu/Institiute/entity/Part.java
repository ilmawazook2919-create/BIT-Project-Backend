package com.edu.Institiute.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.ion.Decimal;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="part" )
public class Part {

    @Id
    @Column(name="id")
    private int id;

    @Column(name="partName")
    private String partName;

    @Column(name="partDescription")
    private String partDescription;

    @Column(name="partNumber")
    private String partNumber;

    @Column(name="unitOfMeasure")
    private String unitOfMeasure;

    @Column(name="unitCost", precision = 15, scale = 4)  // Added precision/scale
    private BigDecimal unitCost;  // Changed to BigDecimal

    @Column(name="sellingPrice", precision = 15, scale = 4)  // Added precision/scale
    private BigDecimal sellingPrice;  // Changed to BigDecimal

    @Column(name="weight")
    private Float weight;

    @Column(name="dimensions")
    private Integer dimensions;

    @Column(name="isActive")
    private Boolean isActive;

    @Column(name="partCreatedBy")
    private String partCreatedBy;

    @Column(name="partCreatedDate")
    private Date partCreatedDate;

    @Column(name="partModifyBy")
    private String partModifyBy;

    @Column(name="partModifyDate")
    private Date partModifyDate;

    @ManyToOne
    @JoinColumn(name="status_id", referencedColumnName = "id")
    private Status status;


}
