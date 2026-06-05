package com.edu.Institiute.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrderDto {
    private int id;
    private String orderNumber;
    private CustomerDto customer;
    private WarehouseDto warehouse;
    private Date orderDate;
    private Date requiredDate;
    private BigDecimal totalAmount;
    private int createdBy;
    private Date createdDate;
    private int modifyBy;
    private Date modifyDate;
    private StatusDto status;
}
