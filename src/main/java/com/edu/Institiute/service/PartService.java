package com.edu.Institiute.service;
import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponsePartDto;

import java.sql.SQLException;


public interface PartService {
    CommonResponseDto savePart(RequestRegistryDto dto);
    CommonResponseDto updatePart(RequestRegistryDto dto, int partId);
    CommonResponseDto removePart(int partId);
    PaginatedResponsePartDto allPart() throws SQLException;
    PaginatedResponsePartDto PartById(int partId) throws SQLException;
}
