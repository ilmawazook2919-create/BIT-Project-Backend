package com.edu.Institiute.dto.responseDto;

import com.edu.Institiute.dto.StatusDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseResponseDto {
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
