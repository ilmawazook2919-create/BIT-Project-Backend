package com.edu.Institiute.api;
import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.service.PurchaseOrderItemService;
import com.edu.Institiute.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/PurchaseOrderItem")
public class PurchaseOrderItemController {
    @Autowired
    private PurchaseOrderItemService purchaseOrderItemService;

    @PostMapping
    public ResponseEntity<StandardResponse> savedPurchaseOrderItem(@RequestBody RequestRegistryDto data) {
        CommonResponseDto responseData = purchaseOrderItemService.savePurchaseOrderItem(data);
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
    @PutMapping("{PurchaseOrderItemId}")
    public ResponseEntity<StandardResponse> updatePurchaseOrderItem(@RequestBody RequestRegistryDto data, @PathVariable int PurchaseOrderItem) {
        CommonResponseDto responseData = purchaseOrderItemService.updatePurchaseOrderItem(data, PurchaseOrderItem);
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
    @DeleteMapping("{PurchaseOrderItemId}")
    public ResponseEntity<StandardResponse> deletePurchaseOrderItem(@PathVariable int PurchaseOrderItemId) {
        CommonResponseDto responseData = purchaseOrderItemService.removePurchaseOrderItem(PurchaseOrderItemId);
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
    public ResponseEntity<StandardResponse> getAllPurchaseOrderItem()throws SQLException {
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "PurchaseOrderItem List",
                        purchaseOrderItemService.allPurchaseOrderItem()),
                HttpStatus.OK
        );
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("{PurchaseOrderItemId}")
    public ResponseEntity<StandardResponse> getPurchaseOrderItem(@PathVariable int PurchaseOrderItemId)throws SQLException {
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "PurchaseOrderItem List",
                        purchaseOrderItemService.PurchaseOrderItemById(PurchaseOrderItemId)),
                HttpStatus.OK
        );
    }

}
