package com.edu.Institiute.service;

import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponseCustomerDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponseWarehouseDto;

import java.sql.SQLException;

public interface WarehouseService {
    CommonResponseDto saveWarehouse(RequestRegistryDto dto);
    CommonResponseDto updateWarehouse(RequestRegistryDto dto, String warehouseId);
    PaginatedResponseWarehouseDto WarehouseById(String warehouseId) throws SQLException;
    PaginatedResponseWarehouseDto allWarehouse() throws SQLException;
    CommonResponseDto removeWarehouse(String warehouseId);
}
