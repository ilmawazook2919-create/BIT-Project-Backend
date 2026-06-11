package com.edu.Institiute.service;

import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponsePickingListDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponseSalesOrderItemDto;

import java.sql.SQLException;

public interface PickingListService {
    CommonResponseDto savePickingList(RequestRegistryDto dto);
    CommonResponseDto updatePickingList(RequestRegistryDto dto, int pickingListId);
    CommonResponseDto removePickingList(int pickingListId);
    PaginatedResponsePickingListDto allPickingList() throws SQLException;
    PaginatedResponsePickingListDto PickingListById(int pickingListId) throws SQLException;

}
