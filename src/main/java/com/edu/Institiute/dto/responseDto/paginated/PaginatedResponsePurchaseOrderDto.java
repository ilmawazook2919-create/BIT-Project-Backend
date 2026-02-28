package com.edu.Institiute.dto.responseDto.paginated;
import com.edu.Institiute.dto.responseDto.PurchaseOrderResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedResponsePurchaseOrderDto {
    private Long count;
    private List<PurchaseOrderResponseDto> dataList;
}
