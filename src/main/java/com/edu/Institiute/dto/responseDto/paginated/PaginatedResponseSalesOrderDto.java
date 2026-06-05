package com.edu.Institiute.dto.responseDto.paginated;
import com.edu.Institiute.dto.responseDto.SalesOrderResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedResponseSalesOrderDto {
    private Long count;
    private List<SalesOrderResponseDto> dataList;
}
