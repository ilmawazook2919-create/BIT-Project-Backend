package com.edu.Institiute.dto.responseDto.paginated;

import com.edu.Institiute.dto.responseDto.SupplierResposeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PaginatedResponseSupplierDto {
    private Long count;
    private List<SupplierResposeDto> dataList;
}
