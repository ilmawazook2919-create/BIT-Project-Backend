package com.edu.Institiute.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BinDto {
    private String id;
    private String binCode;
    private String binDescription;
    private String binCreatedBy;
    private Date binCreatedDate;
    private String binModifiedBy;
    private Date binModifiedDate;
    private StatusDto status;
}
