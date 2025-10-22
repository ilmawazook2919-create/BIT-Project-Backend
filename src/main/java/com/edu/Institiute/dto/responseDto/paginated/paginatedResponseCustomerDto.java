package com.edu.Institiute.dto.responseDto.paginated;

import com.edu.Institiute.dto.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class paginatedResponseCustomerDto {

    private Long count;
    private List<CustomerDto> dataList;
}
