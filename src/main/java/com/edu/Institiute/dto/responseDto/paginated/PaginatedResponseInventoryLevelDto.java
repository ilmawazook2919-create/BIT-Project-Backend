package com.edu.Institiute.dto.responseDto.paginated;
import com.edu.Institiute.dto.responseDto.InventoryLevelResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedResponseInventoryLevelDto {
    private Long count;
    private List<InventoryLevelResponseDto> dataList;
}

