package com.edu.Institiute.service;

import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;

public interface CustomerService {
    CommonResponseDto saveCustomer(RequestRegistryDto dto);
    CommonResponseDto updateCustomer(RequestRegistryDto dto, String customerId);
}
