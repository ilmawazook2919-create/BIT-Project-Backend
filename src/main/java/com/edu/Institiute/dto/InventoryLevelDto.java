package com.edu.Institiute.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryLevelDto {
    private int id;
    private PartDto partId;
    private BinDto binId;
    private int quantityOnHand;
    private int minimumStockLevel;
    private int maximumStockLevel;
    private int createdBy;
    private Date createdDate;
    private int modifiedBy;
    private Date modifiedDate;
    private StatusDto status;

}
