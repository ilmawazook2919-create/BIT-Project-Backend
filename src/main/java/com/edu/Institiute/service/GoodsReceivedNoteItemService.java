package com.edu.Institiute.service;

import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponseGoodsReceivedNoteDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponseGoodsReceivedNoteItemDto;

import java.sql.SQLException;

public interface GoodsReceivedNoteItemService {
    CommonResponseDto saveGoodsReceivedNoteItem(RequestRegistryDto dto);
    CommonResponseDto updateGoodsReceivedNoteItem(RequestRegistryDto dto, String goodsReceivedNoteItemId);
    CommonResponseDto removeGoodsReceivedNoteItem(String goodsReceivedNoteItemId);
    PaginatedResponseGoodsReceivedNoteItemDto allGoodsReceivedNoteItem() throws SQLException;
    PaginatedResponseGoodsReceivedNoteItemDto GoodsReceivedNoteItemById(String goodsReceivedNoteItemId) throws SQLException;

}
