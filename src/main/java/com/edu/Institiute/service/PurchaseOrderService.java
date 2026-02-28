package com.edu.Institiute.service;


import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponsePurchaseOrderDto;

import java.sql.SQLException;

public interface PurchaseOrderService {
    CommonResponseDto savePurchaseOrder(RequestRegistryDto responseDto);
    CommonResponseDto updatePurchaseOrder(RequestRegistryDto dto, int PurchaseOrderId);
    CommonResponseDto removePurchaseOrder(int purchaseOrderId);
    PaginatedResponsePurchaseOrderDto allPurchaseOrder() throws SQLException;
    PaginatedResponsePurchaseOrderDto PurchaseOrderById(int purchaseOrderId) throws SQLException;
}
