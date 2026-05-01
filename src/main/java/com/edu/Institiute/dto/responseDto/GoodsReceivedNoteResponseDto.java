package com.edu.Institiute.dto.responseDto;
import com.edu.Institiute.dto.PurchaseOrderDto;
import com.edu.Institiute.dto.StatusDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsReceivedNoteResponseDto {
    private String id;
    private PurchaseOrderDto purchaseOrder;
    private Date receiptDate;
    private int createdBy;
    private Date createdDate;
    private int modifyBy;
    private Date modifyDate;
    private StatusDto status;
}
