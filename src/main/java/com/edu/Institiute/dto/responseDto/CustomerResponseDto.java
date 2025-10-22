package com.edu.Institiute.dto.responseDto;


import com.edu.Institiute.dto.StatusDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDto {

    private int id;
    private String customerFName;
    private String customerLName;
    private String customerContact;
    private String customerEmail;
    private String customerAddress;
    private String customerCreatedBy;
    private Date customerCreatedDate;
    private String customerModifyBy;
    private Date customerModifyDate;
    private StatusDto status;
}
