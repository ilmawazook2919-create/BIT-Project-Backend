package com.edu.Institiute.service.impl;
import com.edu.Institiute.dto.CategoryDto;
import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CategoryResponseDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponseCategoryDto;
import com.edu.Institiute.entity.Category;
import com.edu.Institiute.entity.Status;
import com.edu.Institiute.exception.EntryNotFoundException;
import com.edu.Institiute.repo.CategoryRepo;
import com.edu.Institiute.repo.StatusRepo;
import com.edu.Institiute.service.CategoryService;
import com.edu.Institiute.utill.Generator;
import com.edu.Institiute.utill.mapper.CategoryMapper;
import com.edu.Institiute.utill.mapper.StatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryRegistryImpl implements CategoryService{
    @Autowired
    private Generator generator;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private StatusRepo statusRepo;

    @Autowired
    private StatusMapper statusMapper;

    @Autowired
    private CategoryService categoryService;
    @Override
    public CommonResponseDto saveCategory(RequestRegistryDto dto) {
        System.out.println("Data Object :" + dto);
        try {
            String categoryId = generator.generateFourNumbers();
            Status status = statusRepo.findStatusById(dto.getStatus())
                    .orElseThrow(() -> new EntryNotFoundException("Status not found with id: " + dto.getStatus()));

            System.out.println("Data Object :" + dto);
            CategoryDto categoryDto = new CategoryDto(
                    categoryId,
                    dto.getCategoryName(),
                    dto.getCategoryDescription(),
                    dto.getCategoryCreatedBy(),
                    new Date(),
                    dto.getCategoryModifiedBy(),
                    new Date(),
                    statusMapper.toStatusDto(status)

            );

            System.out.println("Data Object 2:" + categoryDto);
            categoryRepo.save(categoryMapper.dtoToCategoryEntity(categoryDto));

            return new CommonResponseDto(201, "Category saved!", categoryDto.getCategoryName(), new ArrayList<>());
        } catch (Exception e) {
            throw new EntryNotFoundException("Can't Save because of this Error -->  " + e);
        }
    }
    @Override
    public CommonResponseDto updateCategory(RequestRegistryDto dto, String categoryId) {
        try {
            Optional<Status> status = statusRepo.findStatusById(dto.getStatus());

            Category category = categoryRepo.getCategoryByProvideID(categoryId);
            category.setCategoryName(dto.getCategoryName());
            category.setCategoryDescription(dto.getCategoryDescription());
            category.setCategoryCreatedBy(dto.getCategoryCreatedBy());
            category.setCategoryCreatedDate(dto.getCategoryCreatedDate());
            category.setCategoryModifiedBy(dto.getCategoryModifiedBy());
            category.setCategoryModifiedDate(dto.getCategoryModifiedDate());
            category.setStatus(status.get());

            categoryRepo.save(category);
            return new CommonResponseDto(201, "Category  Updated!", category.getCategoryName(), new ArrayList<>());
        }catch (Exception e){
            throw new EntryNotFoundException("Can't Save because of this Error -->  " + e);
        }
    }

    @Override
    public PaginatedResponseCategoryDto CategoryById(String categoryId) throws SQLException {
        try {
            List<Category> allForProvidedCategoryId = categoryRepo.getAllCategory(categoryId);
            List<CategoryResponseDto> categoryResponseArrayList = new ArrayList<>();

            for (Category r : allForProvidedCategoryId) {
                categoryResponseArrayList.add(
                        new CategoryResponseDto(
                                r.getId(),
                                r.getCategoryName(),
                                r.getCategoryDescription(),
                                r.getCategoryCreatedBy(),
                                r.getCategoryCreatedDate(),
                                r.getCategoryModifiedBy(),
                                r.getCategoryModifiedDate(),
                                statusMapper.toStatusDto(r.getStatus())

                        )
                );
            }

            return new PaginatedResponseCategoryDto(
                    categoryRepo.count(),
                    categoryResponseArrayList
            );
        }
        catch (Exception e){
            throw new EntryNotFoundException("Can't find any data for provided ID...!");
        }
    }



    @Override
    public CommonResponseDto removeCategory(String CategoryId) {
        Category Category= categoryRepo.findByCategoryId(CategoryId);


        if (Category != null){
            categoryRepo.delete(Category);
            return new CommonResponseDto(201, "Category was deleted! ", true, new ArrayList<>());
        }
        else {
            throw new EntryNotFoundException("Can't find any category Data...!");
        }
    }

    @Override
    public PaginatedResponseCategoryDto allCategory() throws SQLException {
        try {
            List<Category> allcategoryForProvidedId = categoryRepo.getAllCategories();
            List<CategoryResponseDto> categoryResponseDto = new ArrayList<>();

            for (Category r : allcategoryForProvidedId) {
                categoryResponseDto.add(
                        new CategoryResponseDto(
                                r.getId(),
                                r.getCategoryName(),
                                r.getCategoryDescription(),
                                r.getCategoryCreatedBy(),
                                r.getCategoryCreatedDate(),
                                r.getCategoryModifiedBy(),
                                r.getCategoryModifiedDate(),
                                statusMapper.toStatusDto(r.getStatus())

                        )
                );
            }

            return new PaginatedResponseCategoryDto(
                    categoryRepo.count(),
                    categoryResponseDto
            );
        }catch (Exception e){
            throw new EntryNotFoundException("Can't find any data...!");
        }
    }

}
