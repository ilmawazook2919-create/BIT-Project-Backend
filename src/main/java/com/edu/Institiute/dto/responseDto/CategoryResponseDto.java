package com.edu.Institiute.dto.responseDto;

import com.edu.Institiute.dto.StatusDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDto {
    private String id;
    private String categoryName;
    private String categoryDescription;
    private String categoryCreatedBy;
    private Date categoryCreatedDate;
    private String categoryModifiedBy;
    private Date categoryModifiedDate;
    private StatusDto status;
}
