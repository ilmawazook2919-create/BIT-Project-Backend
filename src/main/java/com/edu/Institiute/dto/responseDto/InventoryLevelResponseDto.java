package com.edu.Institiute.dto.responseDto;

import com.edu.Institiute.dto.BinDto;
import com.edu.Institiute.dto.PartDto;
import com.edu.Institiute.dto.StatusDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryLevelResponseDto {
    private int id;
    private PartDto part;
    private BinDto binId;
    private int quantityOnHand;
    private int minimumStockLevel;
    private int maximumStockLevel;
    private int createdBy;
    private Date createdDate;
    private int modifyBy;
    private Date modifyDate;
    private StatusDto status;



}
