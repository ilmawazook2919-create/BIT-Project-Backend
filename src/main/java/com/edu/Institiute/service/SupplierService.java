package com.edu.Institiute.service;

import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;

import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponseSupplierDto;


import java.sql.SQLException;


public interface SupplierService {
    CommonResponseDto saveSupplier(RequestRegistryDto dto);
    CommonResponseDto updateSupplier(RequestRegistryDto dto, String supplierId);
    PaginatedResponseSupplierDto SupplierById(String supplierId) throws SQLException;
    CommonResponseDto removeSupplier(String supplierId);
    PaginatedResponseSupplierDto allSupplier() throws SQLException;
}
