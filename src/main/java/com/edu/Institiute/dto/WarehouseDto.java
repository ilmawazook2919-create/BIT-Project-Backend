package com.edu.Institiute.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseDto {
    private String id;
    private String locationName;
    private String address;
    private Boolean isActive;
    private String warehouseCreatedBy;
    private Date warehouseCreatedDate;
    private String warehouseModifiedBy;
    private Date warehouseModifiedDate;
    private StatusDto status;


}
