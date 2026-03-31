package com.edu.Institiute.dto.responseDto.paginated;

import com.edu.Institiute.dto.responseDto.InventoryLevelResponseDto;
import com.edu.Institiute.dto.responseDto.PurchaseOrderItemResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedResponsePurchaseOrderItemDto {
    private Long count;
    private List<PurchaseOrderItemResponseDto> dataList;
}
