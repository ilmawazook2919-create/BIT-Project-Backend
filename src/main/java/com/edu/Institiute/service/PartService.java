package com.edu.Institiute.service;
import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponsePartDto;

import java.sql.SQLException;


public interface PartService {
    CommonResponseDto savePart(RequestRegistryDto dto);
    CommonResponseDto updatePart(RequestRegistryDto dto, String partId);
    CommonResponseDto removePart(String partId);
    PaginatedResponsePartDto allPart() throws SQLException;
    PaginatedResponsePartDto PartById(String partId) throws SQLException;
}
