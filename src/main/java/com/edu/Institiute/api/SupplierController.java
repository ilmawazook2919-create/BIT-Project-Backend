package com.edu.Institiute.api;

import com.edu.Institiute.dto.requestDto.RequestRegistryDto;

import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.service.SupplierService;
import com.edu.Institiute.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/supplier")

public class SupplierController {
    @Autowired
private SupplierService supplierService;

    @PostMapping
    public ResponseEntity<StandardResponse> savedSupplier(@RequestBody RequestRegistryDto data){
        CommonResponseDto responseData = supplierService.saveSupplier(data);
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
    @PutMapping("{SupplierId}")
    public ResponseEntity<StandardResponse> updateSupplier(@RequestBody RequestRegistryDto data, @PathVariable String SupplierId) {
        CommonResponseDto responseData = supplierService.updateSupplier(data, SupplierId);
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
    @DeleteMapping("{SupplierId}")
    public ResponseEntity<StandardResponse> deleteSupplier(@PathVariable String SupplierId){
        CommonResponseDto responseData = supplierService.removeSupplier(SupplierId);
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
    @GetMapping("{SupplierId}")
    public ResponseEntity<StandardResponse> getSupplier(@PathVariable String SupplierId)throws SQLException {
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "Customer List",
                        supplierService.SupplierById(SupplierId)),
                HttpStatus.OK
        );
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping
    public ResponseEntity<StandardResponse> getAllSupplier()throws SQLException{
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        " Supplier List",
                        supplierService.allSupplier()),
                HttpStatus.OK
        );
    }


}

