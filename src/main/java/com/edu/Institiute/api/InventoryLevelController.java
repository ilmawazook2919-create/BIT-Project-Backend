package com.edu.Institiute.api;
import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.entity.InventoryLevel;
import com.edu.Institiute.service.InventoryLevelService;
import com.edu.Institiute.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

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
    @CrossOrigin(origins = "http://localhost:4200/")
    @PutMapping("{InventoryLevelId}")
    public ResponseEntity<StandardResponse> updateInventoryLevel(@RequestBody RequestRegistryDto data, @PathVariable int InventoryLevelId) {
        CommonResponseDto responseData = inventoryLevelService.updateInventoryLevel(data, InventoryLevelId);
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
    @DeleteMapping("{InventoryLevelId}")
    public ResponseEntity<StandardResponse> deleteInventoryLevel(@PathVariable int InventoryLevelId){
        CommonResponseDto responseData = inventoryLevelService.removeInventoryLevel(InventoryLevelId);
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
    @GetMapping
    public ResponseEntity<StandardResponse> getAllInventoryLevel()throws SQLException {
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "InventoryLevel List",
                        inventoryLevelService.allInventoryLevel()),
                HttpStatus.OK
        );
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("{InventoryLevelId}")
    public ResponseEntity<StandardResponse> getInventoryLevel(@PathVariable int InventoryLevelId)throws SQLException {
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "InventoryLevel List",
                        inventoryLevelService.InventoryLevelById(InventoryLevelId)),
                HttpStatus.OK
        );
    }

}
