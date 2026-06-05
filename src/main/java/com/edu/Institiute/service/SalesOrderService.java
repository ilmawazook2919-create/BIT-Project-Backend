package com.edu.Institiute.service;

import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponseSalesOrderDto;

import java.sql.SQLException;

public interface SalesOrderService {
    CommonResponseDto saveSalesOrder(RequestRegistryDto dto);
    CommonResponseDto updateSalesOrder(RequestRegistryDto dto, int salesOrderId);
    CommonResponseDto removeSalesOrder(int salesOrderId);
    PaginatedResponseSalesOrderDto allSalesOrder() throws SQLException;
    PaginatedResponseSalesOrderDto SalesOrderById(int salesOrderId) throws SQLException;
}
