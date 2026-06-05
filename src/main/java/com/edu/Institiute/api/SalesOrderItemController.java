package com.edu.Institiute.api;

import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.service.SalesOrderItemService;
import com.edu.Institiute.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/salesOrderItem")
public class SalesOrderItemController {
    @Autowired
    private SalesOrderItemService salesOrderItemService;
    @PostMapping
    public ResponseEntity<StandardResponse> savedSalesOrderItem(@RequestBody RequestRegistryDto data){
        CommonResponseDto responseData =salesOrderItemService.saveSalesOrderItem(data);
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
    @PutMapping("{SalesOrderItemId}")
    public ResponseEntity<StandardResponse> updateSalesOrderItem(@RequestBody RequestRegistryDto data, @PathVariable int SalesOrderItemId) {
        CommonResponseDto responseData = salesOrderItemService.updateSalesOrderItem(data, SalesOrderItemId);
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
    @DeleteMapping("{SalesOrderItemId}")
    public ResponseEntity<StandardResponse> deleteSalesOrderItem(@PathVariable int SalesOrderItemId){
        CommonResponseDto responseData = salesOrderItemService.removeSalesOrderItem(SalesOrderItemId);
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
    public ResponseEntity<StandardResponse> getAllSalesOrderItem()throws SQLException {
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "SalesOrderItem List",
                        salesOrderItemService.allSalesOrderItem()),
                HttpStatus.OK
        );
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("{SalesOrderItemId}")
    public ResponseEntity<StandardResponse> getSalesOrderItem(@PathVariable int SalesOrderItemId)throws SQLException {
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "SalesOrderItemList",
                        salesOrderItemService.SalesOrderItemById(SalesOrderItemId)),
                HttpStatus.OK
        );
    }
}
