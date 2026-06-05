package com.edu.Institiute.service;

import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponseSalesOrderDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponseSalesOrderItemDto;

import java.sql.SQLException;

public interface SalesOrderItemService {
    CommonResponseDto saveSalesOrderItem(RequestRegistryDto dto);
    CommonResponseDto updateSalesOrderItem(RequestRegistryDto dto, int salesOrderItemId);
    CommonResponseDto removeSalesOrderItem(int salesOrderItemId);
    PaginatedResponseSalesOrderItemDto allSalesOrderItem() throws SQLException;
    PaginatedResponseSalesOrderItemDto SalesOrderItemById(int salesOrderItemId) throws SQLException;
}

