package com.edu.Institiute.service.impl;
import com.edu.Institiute.dto.CustomerDto;
import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.*;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponseCourseDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponseCustomerDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponseStudentDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponseTeacherDto;
import com.edu.Institiute.entity.*;
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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    @Autowired
    private CustomerService customerService;

    @Override
    public CommonResponseDto saveCustomer(RequestRegistryDto dto) {
        System.out.println("Data Object :" + dto);
        try {
            int customerId = generator.generateIntFourNumbers();
            Status status = statusRepo.findStatusById(dto.getStatus())
                    .orElseThrow(() -> new EntryNotFoundException("Status not found with id: " + dto.getStatus()));

            System.out.println("Data Object :" + dto);
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
                    null,
                    statusMapper.toStatusDto(status)

            );

            System.out.println("Data Object 2:" + customerDto);
            customerRepo.save(customerMapper.dtoToCustomerEntity(customerDto));

            return new CommonResponseDto(201, "Customer saved!", customerDto.getCustomerFName(), new ArrayList<>());
        } catch (Exception e) {
            throw new EntryNotFoundException("Can't Save because of this Error -->  " + e);
        }
    }

    @Override
    public CommonResponseDto updateCustomer(RequestRegistryDto dto, String customerId) {
        try {
            Optional<Status> status = statusRepo.findStatusById(dto.getStatus());

            Customer customer = customerRepo.getCustomerByProvideID(customerId);
            customer.setCustomerFName(dto.getCustomerFName());
            customer.setCustomerLName(dto.getCustomerLName());
            customer.setCustomerContact(dto.getCustomerContact());
            customer.setCustomerEmail(dto.getCustomerEmail());
            customer.setCustomerAddress(dto.getCustomerAddress());
            customer.setCustomerCreatedBy(dto.getCustomerCreatedBy());
            customer.setCustomerCreatedDate(dto.getCustomerCreatedDate());
            customer.setCustomerModifyBy(dto.getCustomerModifyBy());
            customer.setCustomerModifyDate(dto.getCustomerModifyDate());
            customer.setStatus(status.get());

            customerRepo.save(customer);
            return new CommonResponseDto(201, "Customer  Updated!", customer.getCustomerFName(), new ArrayList<>());
        }catch (Exception e){
            throw new EntryNotFoundException("Can't Save because of this Error -->  " + e);
        }
    }

    @Override
    public PaginatedResponseCustomerDto CustomerById(String customerId) throws SQLException {
        try {
            List<Customer> allForProvidedCustomerId = customerRepo.getAllCustomer(customerId);
            List<CustomerResponseDto> customerResponseArrayList = new ArrayList<>();

            for (Customer r : allForProvidedCustomerId) {
                customerResponseArrayList.add(
                        new CustomerResponseDto(
                                r.getId(),
                                r.getCustomerFName(),
                                r.getCustomerLName(),
                                r.getCustomerContact(),
                                r.getCustomerEmail(),
                                r.getCustomerAddress(),
                                r.getCustomerCreatedBy(),
                                r.getCustomerCreatedDate(),
                                r.getCustomerModifyBy(),
                                r.getCustomerModifyDate(),
                                statusMapper.toStatusDto(r.getStatus())

                        )
                );
            }

            return new PaginatedResponseCustomerDto(
                    customerRepo.count(),
                    customerResponseArrayList
            );
        }catch (Exception e){
            throw new EntryNotFoundException("Can't find any data for provided ID...!");
        }
    }

    @Override
    public CommonResponseDto removeCustomer(String CustomerId) {
        Customer Customer= customerRepo.findByCustomerId(CustomerId);


        if (!Customer.equals(null)){
            customerRepo.delete(Customer);
            return new CommonResponseDto(201, "customer was deleted! ", true, new ArrayList<>());
            }
        else {
            throw new EntryNotFoundException("Can't find any customer Data...!");
        }
    }

    @Override
    public PaginatedResponseCustomerDto allCustomer() throws SQLException {
        try {
            List<Customer> allcustomerForProvidedId = customerRepo.findAll();
            List<CustomerResponseDto> customerResponseDto = new ArrayList<>();

            for (Customer r : allcustomerForProvidedId) {
                customerResponseDto.add(
                        new CustomerResponseDto(
                                r.getId(),
                                r.getCustomerFName(),
                                r.getCustomerLName(),
                                r.getCustomerContact(),
                                r.getCustomerEmail(),
                                r.getCustomerAddress(),
                                r.getCustomerCreatedBy(),
                                r.getCustomerCreatedDate(),
                                r.getCustomerModifyBy(),
                                r.getCustomerModifyDate(),
                                statusMapper.toStatusDto(r.getStatus())

                        )
                );
            }

            return new PaginatedResponseCustomerDto(
                    customerRepo.count(),
                    customerResponseDto
            );
        }catch (Exception e){
            throw new EntryNotFoundException("Can't find any data...!");
        }
    }
}

