package com.edu.Institiute.dto.responseDto.paginated;
import com.edu.Institiute.dto.responseDto.BinResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedResponseBinDto {
    private Long count;
    private List<BinResponseDto> dataList;
}
