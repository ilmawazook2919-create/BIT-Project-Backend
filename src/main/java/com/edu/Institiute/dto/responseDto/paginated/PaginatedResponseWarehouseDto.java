package com.edu.Institiute.dto.responseDto.paginated;

import com.edu.Institiute.dto.responseDto.CustomerResponseDto;
import com.edu.Institiute.dto.responseDto.WarehouseResponseDto;
import com.edu.Institiute.entity.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedResponseWarehouseDto {
    private Long count;
    private List<WarehouseResponseDto> dataList;

}
