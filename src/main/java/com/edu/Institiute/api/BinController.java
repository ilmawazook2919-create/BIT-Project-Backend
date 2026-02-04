package com.edu.Institiute.api;
import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.service.BinService;
import com.edu.Institiute.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/bin")
public class BinController {
    @Autowired
    private BinService binService;

    @PostMapping
    public ResponseEntity<StandardResponse> savedBin(@RequestBody RequestRegistryDto data){
        CommonResponseDto responseData = binService.saveBin(data);
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
    @PutMapping("{BinId}")
    public ResponseEntity<StandardResponse> updateBin(@RequestBody RequestRegistryDto data, @PathVariable String BinId) {
        CommonResponseDto responseData = binService.updateBin(data, BinId);
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
        @DeleteMapping("{BinId}")
        public ResponseEntity<StandardResponse> deleteBin(@PathVariable String BinId){
            CommonResponseDto responseData = binService.removeBin(BinId);
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
         public ResponseEntity<StandardResponse> getAllBin()throws SQLException {
            return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "Bin List",
                        binService.allBin()),
                HttpStatus.OK
        );
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("{BinId}")
    public ResponseEntity<StandardResponse> getBin(@PathVariable String BinId)throws SQLException {
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "Bin List",
                        binService.BinById(BinId)),
                HttpStatus.OK
        );
    }

}


