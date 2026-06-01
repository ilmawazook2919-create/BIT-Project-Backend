package com.edu.Institiute.api;

import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.service.GoodsReceivedNoteItemService;
import com.edu.Institiute.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/goodsReceivedNoteItem")
public class GoodsReceivedNoteItemController {
    @Autowired
    private GoodsReceivedNoteItemService goodsReceivedNoteItemService;
    @PostMapping
    public ResponseEntity<StandardResponse> savedGoodsReceivedNoteItem(@RequestBody RequestRegistryDto data){
        CommonResponseDto responseData =goodsReceivedNoteItemService.saveGoodsReceivedNoteItem(data);
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
    @PutMapping("{GoodsReceivedNoteItemId}")
    public ResponseEntity<StandardResponse> updateGoodsReceivedNoteItem(@RequestBody RequestRegistryDto data, @PathVariable String GoodsReceivedNoteItemId) {
        CommonResponseDto responseData = goodsReceivedNoteItemService.updateGoodsReceivedNoteItem(data, GoodsReceivedNoteItemId);
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
    @DeleteMapping("{GoodsReceivedNoteItemId}")
    public ResponseEntity<StandardResponse> deleteGoodsReceivedNoteItem(@PathVariable String GoodsReceivedNoteItemId){
        CommonResponseDto responseData = goodsReceivedNoteItemService.removeGoodsReceivedNoteItem(GoodsReceivedNoteItemId);
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
    public ResponseEntity<StandardResponse> getAllGoodsReceivedNoteItem()throws SQLException {
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "GoodsReceivedNoteItem List",
                        goodsReceivedNoteItemService.allGoodsReceivedNoteItem()),
                HttpStatus.OK
        );
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("{GoodsReceivedNoteItemId}")
    public ResponseEntity<StandardResponse> getGoodsReceivedNoteItem(@PathVariable String GoodsReceivedNoteItemId)throws SQLException {
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "GoodsReceivedNoteItemList",
                        goodsReceivedNoteItemService.GoodsReceivedNoteItemById(GoodsReceivedNoteItemId)),
                HttpStatus.OK
        );
    }
}
