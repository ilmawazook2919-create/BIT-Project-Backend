package com.edu.Institiute.dto.responseDto;

import com.edu.Institiute.dto.BinDto;
import com.edu.Institiute.dto.GoodsReceivedNoteDto;
import com.edu.Institiute.dto.PartDto;
import com.edu.Institiute.dto.StatusDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsReceivedNoteItemResponseDto {
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
