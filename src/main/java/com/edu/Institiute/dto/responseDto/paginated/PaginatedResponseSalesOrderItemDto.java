package com.edu.Institiute.dto.responseDto.paginated;

import com.edu.Institiute.dto.responseDto.SalesOrderItemResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedResponseSalesOrderItemDto {
    private Long count;
    private List<SalesOrderItemResponseDto> dataList;
}
