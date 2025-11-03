package com.edu.Institiute.dto.responseDto;



import com.edu.Institiute.dto.StatusDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierResposeDto {
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


