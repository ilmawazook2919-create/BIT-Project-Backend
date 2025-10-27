package com.edu.Institiute.dto.responseDto.paginated;

import com.edu.Institiute.dto.responseDto.CustomerResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedResponseCustomerDto {
    private Long count;
    private List<CustomerResponseDto> dataList;
}
