package com.edu.Institiute.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CategoryDto{

    private String id;
    private String categoryName;
    private String categoryDescription;
    private String categoryCreatedBy;
    private Date categoryCreatedDate;
    private String categoryModifiedBy;
    private Date categoryModifiedDate;
    private StatusDto status;
}