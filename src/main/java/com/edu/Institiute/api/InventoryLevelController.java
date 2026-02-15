package com.edu.Institiute.api;


import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.requestDto.RequestTeacherDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.entity.InventoryLevel;
import com.edu.Institiute.service.InventoryLevelService;
import com.edu.Institiute.service.TeacherService;
import com.edu.Institiute.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/inventoryLevel")
public class InventoryLevelController {

    @Autowired
    private InventoryLevelService inventoryLevelService;

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping
    public ResponseEntity<StandardResponse> savedInventoryLevel(@RequestBody RequestRegistryDto data){
        CommonResponseDto responseData = inventoryLevelService.saveInventoryLevel(data);
        return new ResponseEntity<>(
                new StandardResponse(
                        responseData.getCode(),
                        responseData.getMessage(),
                        responseData.getData()
                ),
                HttpStatus.CREATED
        );
    }

}
