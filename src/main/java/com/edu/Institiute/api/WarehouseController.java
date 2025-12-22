package com.edu.Institiute.api;

import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.service.CustomerService;
import com.edu.Institiute.service.WarehouseService;
import com.edu.Institiute.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/warehouse")
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;

    @PostMapping
    public ResponseEntity<StandardResponse> savedWarehouse(@RequestBody RequestRegistryDto data){
        CommonResponseDto responseData = warehouseService.saveWarehouse(data);
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
    @PutMapping("{WarehouseId}")
    public ResponseEntity<StandardResponse> updateWarehouse(@RequestBody RequestRegistryDto data, @PathVariable String WarehouseId) {
        CommonResponseDto responseData = warehouseService.updateWarehouse(data, WarehouseId);
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
    @GetMapping("{WarehouseId}")
    public ResponseEntity<StandardResponse> getWarehouse(@PathVariable String WarehouseId)throws SQLException {
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "Warehouse List",
                        warehouseService.WarehouseById(WarehouseId)),
                HttpStatus.OK
        );
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping
    public ResponseEntity<StandardResponse> getAllWarehouse()throws SQLException{
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "Warehouse List",
                        warehouseService.allWarehouse()),
                HttpStatus.OK
        );
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @DeleteMapping("{WarehouseId}")
    public ResponseEntity<StandardResponse> deleteWarehouse(@PathVariable String WarehouseId){
        CommonResponseDto responseData = warehouseService.removeWarehouse(WarehouseId);
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

