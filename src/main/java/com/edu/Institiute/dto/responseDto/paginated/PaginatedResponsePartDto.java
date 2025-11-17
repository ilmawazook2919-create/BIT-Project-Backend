package com.edu.Institiute.dto.responseDto.paginated;


import com.edu.Institiute.dto.responseDto.PartResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedResponsePartDto {
    private Long count;
    private List<PaginatedResponsePartDto> dataList;

}
