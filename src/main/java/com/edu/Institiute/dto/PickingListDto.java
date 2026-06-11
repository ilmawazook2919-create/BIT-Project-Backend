package com.edu.Institiute.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PickingListDto {
    private int id;
    private SalesOrderDto salesOrderId;
    private Date shipmentDate;
    private UserDto shippedBy;
    private int trackingNumber;
    private int createdBy;
    private Date createdDate;
    private int modifyBy;
    private Date modifyDate;
    private StatusDto status;

}
