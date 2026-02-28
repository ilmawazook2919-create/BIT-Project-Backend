package com.edu.Institiute.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderDto {
        private int id;
        private String purchaseNumber;
        private SupplierDto supplierId;
        private Date orderDate;
        private Date expectedDeliveryDate;
        private BigDecimal totalAmount;
        private int createdBy;
        private Date createdDate;
        private int modifyBy;
        private Date modifyDate;
        private StatusDto status;
    }

