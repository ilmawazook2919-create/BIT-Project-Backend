package com.edu.Institiute.service;
import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponseCategoryDto;

import java.sql.SQLException;

public interface CategoryService {
    CommonResponseDto saveCategory(RequestRegistryDto responseDto);
    CommonResponseDto updateCategory(RequestRegistryDto dto, String categoryId);
    CommonResponseDto removeCategory(String categoryId);
    PaginatedResponseCategoryDto allCategory() throws SQLException;
    PaginatedResponseCategoryDto CategoryById(String categoryId) throws SQLException;

}
