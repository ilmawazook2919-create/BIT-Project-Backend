package com.edu.Institiute.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsReceivedNoteItemDto {
    private String id;
    private GoodsReceivedNoteDto goodsReceivedNoteId;
    private PartDto partId;
    private BinDto binId;
    private String quantity;
    private int createdBy;
    private Date createdDate;
    private int modifyBy;
    private Date modifyDate;
    private StatusDto status;

}
