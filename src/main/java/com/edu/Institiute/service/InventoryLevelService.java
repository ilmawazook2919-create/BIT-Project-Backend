package com.edu.Institiute.service;

import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponseInventoryLevelDto;

import java.sql.SQLException;

public interface InventoryLevelService {
    CommonResponseDto saveInventoryLevel(RequestRegistryDto dto);

    CommonResponseDto updateInventoryLevel(RequestRegistryDto dto, int inventoryLevelId);

    CommonResponseDto removeInventoryLevel(int inventoryLevelId);

    PaginatedResponseInventoryLevelDto allInventoryLevel() throws SQLException;

    PaginatedResponseInventoryLevelDto InventoryLevelById(int inventoryLevelId) throws SQLException;
}