package com.edu.Institiute.api;


import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.service.GoodsReceivedNoteService;
import com.edu.Institiute.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/goodsReceivedNote")
public class GoodsReceivedNoteController {
    @Autowired
    private GoodsReceivedNoteService goodsReceivedNoteService;
    @PostMapping
    public ResponseEntity<StandardResponse> savedGoodsReceivedNote(@RequestBody RequestRegistryDto data){
        CommonResponseDto responseData =goodsReceivedNoteService.saveGoodsReceivedNote(data);
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
    @PutMapping("{GoodsReceivedNoteId}")
    public ResponseEntity<StandardResponse> updateGoodsReceivedNote(@RequestBody RequestRegistryDto data, @PathVariable String GoodsReceivedNoteId) {
        CommonResponseDto responseData = goodsReceivedNoteService.updateGoodsReceivedNote(data, GoodsReceivedNoteId);
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
    @DeleteMapping("{GoodsReceivedNoteId}")
    public ResponseEntity<StandardResponse> deleteGoodsReceivedNote(@PathVariable String GoodsReceivedNoteId){
        CommonResponseDto responseData = goodsReceivedNoteService.removeGoodsReceivedNote(GoodsReceivedNoteId);
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
    public ResponseEntity<StandardResponse> getAllGoodsReceivedNote()throws SQLException {
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "GoodsReceivedNote List",
                        goodsReceivedNoteService.allGoodsReceivedNote()),
                HttpStatus.OK
        );
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("{GoodsReceivedNoteId}")
    public ResponseEntity<StandardResponse> getGoodsReceivedNote(@PathVariable String GoodsReceivedNoteId)throws SQLException {
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "GoodsReceivedNoteList",
                        goodsReceivedNoteService.GoodsReceivedNoteById(GoodsReceivedNoteId)),
                HttpStatus.OK
        );
    }

}
