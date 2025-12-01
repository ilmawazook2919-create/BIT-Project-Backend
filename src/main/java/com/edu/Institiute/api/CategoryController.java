package com.edu.Institiute.api;


import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.service.CategoryService;
import com.edu.Institiute.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@CrossOrigin
@RestController
@RequestMapping
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<StandardResponse> savedCategory(@RequestBody RequestRegistryDto data){
        CommonResponseDto responseData = categoryService.saveCategory(data);
        return new ResponseEntity<>(
                new StandardResponse(
                        responseData.getCode(),
                        responseData.getMessage(),
                        responseData.getData()
                ),
                HttpStatus.CREATED
        );
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @PutMapping("{CategoryId}")
    public ResponseEntity<StandardResponse> updateCategory(@RequestBody RequestRegistryDto data, @PathVariable String CategoryId) {
        CommonResponseDto responseData = categoryService.updateCategory(data, CategoryId);
        return new ResponseEntity<>(
                new StandardResponse(
                        responseData.getCode(),
                        responseData.getMessage(),
                        responseData.getData()
                ),
                HttpStatus.CREATED
        );
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @DeleteMapping("{CategoryId}")
    public ResponseEntity<StandardResponse> deleteCategory(@PathVariable String CategoryId){
        CommonResponseDto responseData = categoryService.removeCategory(CategoryId);
        return new ResponseEntity<>(
                new StandardResponse(
                        responseData.getCode(),
                        responseData.getMessage(),
                        responseData.getData()
                ),
                HttpStatus.CREATED
        );
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("{CategoryId}")
    public ResponseEntity<StandardResponse> getCategory(@PathVariable String CategoryId)throws SQLException {
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "Category List",
                        categoryService.CategoryById(CategoryId)),
                HttpStatus.OK
        );
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping
    public ResponseEntity<StandardResponse> getAllCategory()throws SQLException{
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "Category List",
                        categoryService.allCategory()),
                HttpStatus.OK
        );
    }




}
