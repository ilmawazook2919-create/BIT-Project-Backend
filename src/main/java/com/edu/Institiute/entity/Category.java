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
@Table(name="category")
public class Category {

    @Id
    @Column(name="id")
    private String id;

    @Column(name ="categoryName")
    private String categoryName;

    @Column(name = "categoryDescription")
    private String categoryDescription;

    @Column(name = "categoryCreatedBy")
    private String categoryCreatedBy;

    @Column(name = "categoryCreatedDate")
    private Date categoryCreatedDate;

    @Column(name = "categoryModifiedBy")
    private String categoryModifiedBy;

    @Column(name = "categoryModifiedDate")
    private Date categoryModifiedDate;

    @ManyToOne
    @JoinColumn(name="status_id", referencedColumnName = "id")
    private Status status;

}
