package com.edu.Institiute.service.impl;
import com.edu.Institiute.dto.CustomerDto;
import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.entity.Status;
import com.edu.Institiute.exception.EntryNotFoundException;
import com.edu.Institiute.repo.CustomerRepo;
import com.edu.Institiute.repo.StatusRepo;
import com.edu.Institiute.service.CustomerService;
import com.edu.Institiute.utill.Generator;
import com.edu.Institiute.utill.mapper.CustomerMapper;
import com.edu.Institiute.utill.mapper.StatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class CustomerRegistryImpl implements CustomerService {

    @Autowired
    private Generator generator;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private StatusRepo statusRepo;

    @Autowired
    private StatusMapper statusMapper;

    @Override
    public CommonResponseDto saveCustomer(RequestRegistryDto dto) {
        System.out.println("Data Object :"+ dto);
        try {
            int customerId =  generator.generateIntFourNumbers();
            Status status = statusRepo.findStatusById(dto.getStatus())
                    .orElseThrow(() -> new EntryNotFoundException("Status not found with id: " + dto.getStatus()));

            System.out.println("Data Object :"+ dto);
            CustomerDto customerDto = new CustomerDto(
                    customerId,
                    dto.getCustomerFName(),
                    dto.getCustomerLName(),
                    dto.getCustomerContact(),
                    dto.getCustomerEmail(),
                    dto.getCustomerAddress(),
                    dto.getCustomerCreatedBy(),
                    new Date(),
                    dto.getCustomerModifyBy(),
                    new Date(),
                    statusMapper.toStatusDto(status)

            );

            System.out.println("Data Object 2:"+ customerDto);
            customerRepo.save(customerMapper.dtoToCustomerEntity(customerDto));

            return new CommonResponseDto(201, "Customer saved!", customerDto.getCustomerFName(), new ArrayList<>());
        }catch (Exception e){
            throw new EntryNotFoundException("Can't Save because of this Error -->  " + e);
        }
    }

    @Override
    public CommonResponseDto updateCustomer(RequestRegistryDto dto, String customerId) {
        return null;
    }
}
