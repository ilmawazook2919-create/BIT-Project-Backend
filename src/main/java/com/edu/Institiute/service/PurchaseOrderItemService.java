package com.edu.Institiute.service;

import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponsePurchaseOrderItemDto;

import java.sql.SQLException;

public interface PurchaseOrderItemService {
    CommonResponseDto savePurchaseOrderItem(RequestRegistryDto dto);
    CommonResponseDto updatePurchaseOrderItem(RequestRegistryDto dto, int PurchaseOrderItemId);
    CommonResponseDto removePurchaseOrderItem(int purchaseOrderItemId);
    PaginatedResponsePurchaseOrderItemDto allPurchaseOrderItem() throws SQLException;
    PaginatedResponsePurchaseOrderItemDto PurchaseOrderItemById(int purchaseOrderItemId) throws SQLException;


}
