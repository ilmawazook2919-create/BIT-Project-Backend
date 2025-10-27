package com.edu.Institiute.service;

import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponseCourseDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponseCustomerDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponseStudentDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponseTeacherDto;

import java.sql.SQLException;

public interface CustomerService {
    CommonResponseDto saveCustomer(RequestRegistryDto dto);
    CommonResponseDto updateCustomer(RequestRegistryDto dto, String customerId);
  PaginatedResponseCustomerDto CustomerById(String customerId) throws SQLException;
    CommonResponseDto removeCustomer(String customerId);
    PaginatedResponseCustomerDto allCustomer() throws SQLException;
}
