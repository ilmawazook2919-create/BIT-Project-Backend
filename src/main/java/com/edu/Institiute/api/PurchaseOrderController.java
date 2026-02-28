package com.edu.Institiute.api;

import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.service.BinService;
import com.edu.Institiute.service.PurchaseOrderService;
import com.edu.Institiute.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/PurchaseOrder")
public class PurchaseOrderController {
    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @PostMapping
    public ResponseEntity<StandardResponse> savedPurchaseOrder(@RequestBody RequestRegistryDto data){
        CommonResponseDto responseData = purchaseOrderService.savePurchaseOrder(data);
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
    @PutMapping("{PurchaseOrderId}")
    public ResponseEntity<StandardResponse> updatePurchaseOrder(@RequestBody RequestRegistryDto data, @PathVariable int PurchaseOrderId) {
        CommonResponseDto responseData = purchaseOrderService.updatePurchaseOrder(data, PurchaseOrderId);
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
    @DeleteMapping("{PurchaseOrderId}")
    public ResponseEntity<StandardResponse> deletePurchaseOrder(@PathVariable int PurchaseOrderId){
        CommonResponseDto responseData =purchaseOrderService.removePurchaseOrder(PurchaseOrderId);
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
    public ResponseEntity<StandardResponse> getAllPurchaseOrder()throws SQLException {
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "purchaseOrder List",
                        purchaseOrderService.allPurchaseOrder()),
                HttpStatus.OK
        );
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("{PurchaseOrderId}")
    public ResponseEntity<StandardResponse> getPurchaseOrder(@PathVariable int PurchaseOrderId)throws SQLException {
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "PurchaseOrder List",
                        purchaseOrderService.PurchaseOrderById(PurchaseOrderId)),
                HttpStatus.OK
        );
    }

}
