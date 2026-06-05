package com.edu.Institiute.api;

import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.service.SalesOrderService;
import com.edu.Institiute.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/salesOrder")
public class SalesOrderController {
    @Autowired
    private SalesOrderService salesOrderService;
    @PostMapping
    public ResponseEntity<StandardResponse> savedSalesOrder(@RequestBody RequestRegistryDto data){
        CommonResponseDto responseData =salesOrderService.saveSalesOrder(data);
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
    @PutMapping("{SalesOrderId}")
    public ResponseEntity<StandardResponse> updateSalesOrder(@RequestBody RequestRegistryDto data, @PathVariable int SalesOrderId) {
        CommonResponseDto responseData = salesOrderService.updateSalesOrder(data, SalesOrderId);
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
    @DeleteMapping("{SalesOrderId}")
    public ResponseEntity<StandardResponse> deleteSalesOrder(@PathVariable int SalesOrderId){
        CommonResponseDto responseData = salesOrderService.removeSalesOrder(SalesOrderId);
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
    public ResponseEntity<StandardResponse> getAllSalesOrder()throws SQLException {
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "SalesOrder List",
                        salesOrderService.allSalesOrder()),
                HttpStatus.OK
        );
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("{SalesOrderId}")
    public ResponseEntity<StandardResponse> getSalesOrder(@PathVariable int SalesOrderId)throws SQLException {
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "SalesOrderList",
                        salesOrderService.SalesOrderById(SalesOrderId)),
                HttpStatus.OK
        );
    }
}
