package com.edu.Institiute.dto.responseDto;
import com.edu.Institiute.dto.PartDto;
import com.edu.Institiute.dto.PurchaseOrderDto;
import com.edu.Institiute.dto.StatusDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderItemResponseDto {
    private int id;
    private PurchaseOrderDto purchaseOrderId;
    private PartDto partId;
    private int quantityOrdered;
    private BigDecimal unitCost;
    private int createdBy;
    private Date createdDate;
    private int modifyBy;
    private Date modifyDate;
    private StatusDto status;
}
