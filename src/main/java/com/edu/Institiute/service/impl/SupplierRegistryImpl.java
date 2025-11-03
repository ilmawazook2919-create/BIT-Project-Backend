package com.edu.Institiute.service.impl;
import com.edu.Institiute.dto.StatusDto;
import com.edu.Institiute.dto.SupplierDto;
import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.*;
import com.edu.Institiute.dto.responseDto.paginated.*;
import com.edu.Institiute.entity.*;
import com.edu.Institiute.exception.EntryNotFoundException;
import com.edu.Institiute.repo.SupplierRepo;
import com.edu.Institiute.repo.StatusRepo;
import com.edu.Institiute.service.SupplierService;
import com.edu.Institiute.utill.Generator;
import com.edu.Institiute.utill.mapper.SupplierMapper;
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
public class SupplierRegistryImpl implements SupplierService {

    @Autowired
    private Generator generator;

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private SupplierRepo supplierRepo;

    @Autowired
    private StatusRepo statusRepo;

    @Autowired
    private StatusMapper statusMapper;
    @Autowired
    private SupplierService supplierService;
    @Override
    public CommonResponseDto saveSupplier(RequestRegistryDto dto) {
        System.out.println("Data Object :" + dto);
        try {
            int supplierId = generator.generateIntFourNumbers();
            Status status = statusRepo.findStatusById(dto.getStatus())
                    .orElseThrow(() -> new EntryNotFoundException("Status not found with id: " + dto.getStatus()));

            System.out.println("Data Object :" + dto);
            SupplierDto SupplierDto = new SupplierDto(
                    supplierId,
                    dto.getSupplierName(),
                    dto.getSupplierContact(),
                    dto.getSupplierEmail(),
                    dto.getSupplierAddress(),
                    dto.getSupplierContactPerson(),
                    dto.getSupplierCreatedBy(),
                    new Date(),
                    dto.getSupplierModifyBy(),
                    new Date(),
                    statusMapper.toStatusDto(status)

            );

            System.out.println("Data Object 2:" + SupplierDto);
            supplierRepo.save(supplierMapper.dtoToSupplierEntity(SupplierDto));

            return new CommonResponseDto(201, "Supplier saved!", SupplierDto.getSupplierName(), new ArrayList<>());
        } catch (Exception e) {
            throw new EntryNotFoundException("Can't Save because of this Error -->  " + e);
        }
    }


        @Override
        public CommonResponseDto updateSupplier(RequestRegistryDto dto, String supplierId){
            try {
                Optional<Status> status = statusRepo.findStatusById(dto.getStatus());

                Supplier supplier = supplierRepo.getSupplierByProvideID(supplierId);
                supplier.setSupplierName(dto.getSupplierName());
                supplier.setSupplierContact(dto.getSupplierContact());
                supplier.setSupplierEmail(dto.getSupplierEmail());
                supplier.setSupplierAddress(dto.getSupplierAddress());
                supplier.setSupplierContactPerson(dto.getSupplierContactPerson());
                supplier.setSupplierCreatedBy(dto.getSupplierCreatedBy());
                supplier.setSupplierCreatedDate(dto.getSupplierCreatedDate());
                supplier.setSupplierModifyBy(dto.getSupplierModifyBy());
                supplier.setSupplierModifyDate(dto.getSupplierModifyDate());
                supplier.setStatus(status.get());

                supplierRepo.save(supplier);
                return new CommonResponseDto(201, "Supplier  Updated!", supplier.getSupplierName(), new ArrayList<>());
            } catch (Exception e) {
                throw new EntryNotFoundException("Can't Save because of this Error -->  " + e);
            }
        }
        @Override
    public CommonResponseDto removeSupplier(String SupplierId) {
        Supplier Supplier= supplierRepo.findBySupplierId(SupplierId);


        if (!Supplier.equals(null)){
            supplierRepo.delete(Supplier);
            return new CommonResponseDto(201, "Supplier was deleted! ", true, new ArrayList<>());
        }
        else {
            throw new EntryNotFoundException("Can't find any Supplier Data...!");
        }
    }
    @Override

    public PaginatedResponseSupplierDto allSupplier() throws SQLException {
        try {
            List<Supplier> allsupplier = supplierRepo.findAll();
            List<SupplierResposeDto> supplierResponseDtos= new ArrayList<>();

            for (Supplier r : allsupplier) {
                supplierResponseDtos.add(
                        new SupplierResposeDto(
                                r.getId(),
                                r.getSupplierName(),
                                r.getSupplierContact(),
                                r.getSupplierEmail(),
                                r.getSupplierAddress(),
                                r.getSupplierContactPerson(),
                                r.getSupplierCreatedBy(),
                                r.getSupplierCreatedDate(),
                                r.getSupplierModifyBy(),
                                r.getSupplierModifyDate(),
                                statusMapper.toStatusDto(r.getStatus())

                        ) );

            }

            return new PaginatedResponseSupplierDto(
                    supplierRepo.count(),
                    supplierResponseDtos
            );
        } catch (Exception e){
            throw new EntryNotFoundException("Can't find any data...!");
            }
    }

    @Override
    public PaginatedResponseSupplierDto SupplierById(String supplierId) throws SQLException {
        try {
            List<Supplier> allForProvidedSupplierId = supplierRepo.getAllSupplier(supplierId);
            List<SupplierResposeDto> supplierResponseArrayList = new ArrayList<>();

            for (Supplier r : allForProvidedSupplierId) {
                supplierResponseArrayList.add(
                        new SupplierResposeDto(
                                r.getId(),
                                r.getSupplierName(),
                                r.getSupplierContact(),
                                r.getSupplierEmail(),
                                r.getSupplierAddress(),
                                r.getSupplierContactPerson(),
                                r.getSupplierCreatedBy(),
                                r.getSupplierCreatedDate(),
                                r.getSupplierModifyBy(),
                                r.getSupplierModifyDate(),
                                statusMapper.toStatusDto(r.getStatus())


                        )
                );
            }

            return new PaginatedResponseSupplierDto(
                    supplierRepo.count(),
                    supplierResponseArrayList
            );
        }catch (Exception e){
            throw new EntryNotFoundException("Can't find any data for provided ID...!");
        }
    }
    }