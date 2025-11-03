package com.edu.Institiute.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SupplierDto {
    private int id;
    private String supplierName;
    private String supplierContact;
    private String supplierEmail;
    private String supplierAddress;
    private String supplierContactPerson;
    private String supplierCreatedBy;
    private Date supplierCreatedDate;
    private String supplierModifyBy;
    private Date supplierModifyDate;
    private StatusDto status;
}

