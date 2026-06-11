package com.edu.Institiute.dto.responseDto;

import com.edu.Institiute.dto.SalesOrderDto;
import com.edu.Institiute.dto.StatusDto;
import com.edu.Institiute.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PickingListResponseDto {
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
