package com.edu.Institiute.dto.responseDto.paginated;

import com.edu.Institiute.dto.responseDto.GoodsReceivedNoteItemResponseDto;
import com.edu.Institiute.dto.responseDto.GoodsReceivedNoteResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedResponseGoodsReceivedNoteItemDto {
    private Long count;
    private List<GoodsReceivedNoteItemResponseDto> dataList;

}
