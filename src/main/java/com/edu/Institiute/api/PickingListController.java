package com.edu.Institiute.api;

import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.service.PickingListService;
import com.edu.Institiute.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/pickingList")
public class PickingListController {
    @Autowired
    private PickingListService pickingListService;
    @PostMapping
    public ResponseEntity<StandardResponse> savedPickingList(@RequestBody RequestRegistryDto data){
        CommonResponseDto responseData =pickingListService.savePickingList(data);
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
    @PutMapping("{PickingListId}")
    public ResponseEntity<StandardResponse> updatePickingList(@RequestBody RequestRegistryDto data, @PathVariable int PickingListId) {
        CommonResponseDto responseData = pickingListService.updatePickingList(data, PickingListId);
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
    @DeleteMapping("{PickingListId}")
    public ResponseEntity<StandardResponse> deletePickingList(@PathVariable int PickingListId){
        CommonResponseDto responseData = pickingListService.removePickingList(PickingListId);
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
    public ResponseEntity<StandardResponse> getAllPickingList()throws SQLException {
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "PickingList List",
                        pickingListService.allPickingList()),
                HttpStatus.OK
        );
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("{PickingListId}")
    public ResponseEntity<StandardResponse> getPickingList(@PathVariable int PickingListId)throws SQLException {
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "PickingList",
                        pickingListService.PickingListById(PickingListId)),
                HttpStatus.OK
        );
    }
}
