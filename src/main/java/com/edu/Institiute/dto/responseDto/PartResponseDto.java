package com.edu.Institiute.dto.responseDto;

import com.edu.Institiute.dto.StatusDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.ion.Decimal;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartResponseDto {
    private int id;
    private String partName;
    private String partDescription;
    private String partNumber;
    private String unitOfMeasure;
    private Decimal unitCost;
    private Decimal sellingPrice;
    private Float weight;
    private Integer dimensions;
    private Boolean isActive;
    private String partCreatedBy;
    private Date partCreatedDate;
    private String partModifiedBy;
    private Date partModifiedDate;
    private StatusDto status;

}
