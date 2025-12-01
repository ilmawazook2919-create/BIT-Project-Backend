package com.edu.Institiute.utill.mapper;

import com.edu.Institiute.dto.CategoryDto;
import com.edu.Institiute.entity.Category;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category dtoToCategoryEntity(CategoryDto categoryDto);

    CategoryDto toCategoryDto(Category category);


}
