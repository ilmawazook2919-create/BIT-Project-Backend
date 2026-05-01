package com.edu.Institiute.dto.responseDto.paginated;

import com.edu.Institiute.dto.PurchaseOrderDto;
import com.edu.Institiute.dto.StatusDto;
import com.edu.Institiute.dto.UserDto;
import com.edu.Institiute.dto.responseDto.GoodsReceivedNoteResponseDto;
import com.edu.Institiute.dto.responseDto.InventoryLevelResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedResponseGoodsReceivedNoteDto {
    private Long count;
    private List<GoodsReceivedNoteResponseDto> dataList;
}
