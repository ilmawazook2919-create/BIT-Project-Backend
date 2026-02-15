package com.edu.Institiute.service;

import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;

public interface InventoryLevelService {
     CommonResponseDto saveInventoryLevel(RequestRegistryDto dto);
}
