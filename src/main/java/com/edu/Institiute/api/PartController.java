package com.edu.Institiute.api;


import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.service.PartService;
import com.edu.Institiute.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/invetorypart")
public class PartController {

    @Autowired
    private PartService partService;

    @PostMapping
    public ResponseEntity<StandardResponse> savedPart(@RequestBody RequestRegistryDto data){
        CommonResponseDto responseData = partService.savePart(data);
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
    @PutMapping("{PartId}")
    public ResponseEntity<StandardResponse> updatePart(@RequestBody RequestRegistryDto data, @PathVariable String PartId) {
        CommonResponseDto responseData = partService.updatePart(data, PartId);
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
    @DeleteMapping("{PartId}")
    public ResponseEntity<StandardResponse> deletePart(@PathVariable String PartId){
        CommonResponseDto responseData = partService.removePart(PartId);
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
    @GetMapping("{PartId}")
    public ResponseEntity<StandardResponse> getPart(@PathVariable String PartId)throws SQLException {
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "Part List",
                        partService.PartById(PartId)),
                HttpStatus.OK
        );
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping
    public ResponseEntity<StandardResponse> getAllPart()throws SQLException{
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "Part List",
                        partService.allPart()),
                HttpStatus.OK
        );
    }
}
