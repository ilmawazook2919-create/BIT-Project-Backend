package com.edu.Institiute.dto.requestDto;

import com.edu.Institiute.dto.StatusDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class RequestRegistryDto {




    // Student
    private String studentCode;
    private String studentName;
    private String studentAge;
    private String studentNic;
    private Integer status;

    // Course
    private String courseCode;
    private String courseName;


    // Customer
    private String customerFName;
    private String customerLName;
    private String customerContact;
    private String customerEmail;
    private String customerAddress;
    private String customerCreatedBy;
    private Date customerCreatedDate;
    private String customerModifyBy;
    private Date customerModifyDate;

    // Supplier

    private String supplierName;
    private String supplierContact;
    private String supplierEmail;
    private String supplierAddress;
    private String supplierContactPerson;
    private String supplierCreatedBy;
    private Date supplierCreatedDate;
    private String supplierModifyBy;
    private Date supplierModifyDate;

}



