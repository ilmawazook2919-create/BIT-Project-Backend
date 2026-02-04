package com.edu.Institiute.service;
import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponseBinDto;
import java.sql.SQLException;

public interface BinService {
    CommonResponseDto saveBin(RequestRegistryDto responseDto);
    CommonResponseDto updateBin(RequestRegistryDto dto, String binId);
    CommonResponseDto removeBin(String binId);
    PaginatedResponseBinDto allBin() throws SQLException;
    PaginatedResponseBinDto BinById(String binId) throws SQLException;
}
