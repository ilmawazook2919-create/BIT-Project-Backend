package com.edu.Institiute.service;

import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponseGoodsReceivedNoteDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponseInventoryLevelDto;

import java.sql.SQLException;

public interface GoodsReceivedNoteService {
    CommonResponseDto saveGoodsReceivedNote(RequestRegistryDto dto);
    CommonResponseDto updateGoodsReceivedNote(RequestRegistryDto dto, String goodsReceivedNoteId);
    CommonResponseDto removeGoodsReceivedNote(String goodsReceivedNoteId);
    PaginatedResponseGoodsReceivedNoteDto allGoodsReceivedNote() throws SQLException;
    PaginatedResponseGoodsReceivedNoteDto GoodsReceivedNoteById(String goodsReceivedNoteId) throws SQLException;


}
