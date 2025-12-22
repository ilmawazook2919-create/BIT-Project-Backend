package com.edu.Institiute.service.impl;

import com.edu.Institiute.dto.WarehouseDto;
import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.dto.responseDto.WarehouseResponseDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponseWarehouseDto;
import com.edu.Institiute.entity.Status;
import com.edu.Institiute.entity.Warehouse;
import com.edu.Institiute.exception.EntryNotFoundException;
import com.edu.Institiute.repo.StatusRepo;
import com.edu.Institiute.repo.WarehouseRepo;
import com.edu.Institiute.service.WarehouseService;
import com.edu.Institiute.utill.Generator;
import com.edu.Institiute.utill.mapper.StatusMapper;
import com.edu.Institiute.utill.mapper.WarehouseMapper;
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
public class WarehouseRegistryImpl implements WarehouseService {

    @Autowired
    private Generator generator;

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Autowired
    private WarehouseRepo warehouseRepo;

    @Autowired
    private StatusRepo statusRepo;

    @Autowired
    private StatusMapper statusMapper;

    @Autowired
    private WarehouseService warehouseService;
    @Override
    public CommonResponseDto saveWarehouse(RequestRegistryDto dto) {
        System.out.println("Data Object :" + dto);
        try {
            int warehouseId = generator.generateIntFourNumbers();
            Status status = statusRepo.findStatusById(dto.getStatus())
                    .orElseThrow(() -> new EntryNotFoundException("Status not found with id: " + dto.getStatus()));

            System.out.println("Data Object :" + dto);
            WarehouseDto warehouseDto = new WarehouseDto(
                    String.valueOf(warehouseId),
                    dto.getLocationName(),
                    dto.getAddress(),
                    dto.getIsActive(),
                    dto.getWarehouseCreatedBy(),
                    new Date(),
                    dto.getWarehouseModifiedBy(),
                    null,
                    statusMapper.toStatusDto(status)
            );

            System.out.println("Data Object 2:" + warehouseDto);
            warehouseRepo.save(warehouseMapper.dtoToWarehouseEntity(warehouseDto));

            return new CommonResponseDto(201, "Warehouse saved!", warehouseDto.getLocationName(), new ArrayList<>());
        } catch (Exception e) {
            throw new EntryNotFoundException("Can't Save because of this Error -->  " + e);
        }
    }
    @Override
    public CommonResponseDto updateWarehouse(RequestRegistryDto dto, String warehouseId) {
        try {
            Optional<Status> status = statusRepo.findStatusById(dto.getStatus());

            Warehouse warehouse = warehouseRepo.getWarehouseByProvideId(warehouseId);
            warehouse.setLocationName(dto.getLocationName());
            warehouse.setAddress(dto.getAddress());
            warehouse.setIsActive(dto.getIsActive());
            warehouse.setWarehouseCreatedBy(dto.getWarehouseCreatedBy());
            warehouse.setWarehouseCreatedDate(dto.getWarehouseCreatedDate());
            warehouse.setWarehouseModifiedBy(dto.getWarehouseModifiedBy());
            warehouse.setWarehouseModifiedDate(dto.getWarehouseModifiedDate());
            warehouse.setStatus(status.get());

            warehouseRepo.save(warehouse);
            return new CommonResponseDto(201, "Warehouse  Updated!", warehouse.getLocationName(), new ArrayList<>());
        }catch (Exception e){
            throw new EntryNotFoundException("Can't Save because of this Error -->  " + e);
        }
    }

    @Override
    public PaginatedResponseWarehouseDto WarehouseById(String warehouseId) throws SQLException {
        return null;
    }

    @Override
    public PaginatedResponseWarehouseDto allWarehouse() throws SQLException {
        try {
            Iterable<Warehouse> allWarehouseForProvidedId = warehouseRepo.findAll();
            List<WarehouseResponseDto> warehouseResponseDto = new ArrayList<>();

            for (Warehouse r : allWarehouseForProvidedId) {
                warehouseResponseDto.add(
                        new WarehouseResponseDto(
                                r.getId(),
                                r.getLocationName(),
                                r.getAddress(),
                                r.getIsActive(),
                                r.getWarehouseCreatedBy(),
                                r.getWarehouseCreatedDate(),
                                r.getWarehouseModifiedBy(),
                                r.getWarehouseModifiedDate(),
                                statusMapper.toStatusDto(r.getStatus())

                        )
                );
            }

            return new PaginatedResponseWarehouseDto(
                    warehouseRepo.count(),
                    warehouseResponseDto
            );
        }catch (Exception e){
            throw new EntryNotFoundException("Can't find any data...!");
        }
    }
    @Override
    public CommonResponseDto removeWarehouse(String WarehouseId) {
        Warehouse Warehouse= warehouseRepo.findByWarehouseId(WarehouseId);


        if (!Warehouse.equals(null)){
            warehouseRepo.delete(Warehouse);
            return new CommonResponseDto(201, "Warehouse was deleted! ", true, new ArrayList<>());
        }
        else {
            throw new EntryNotFoundException("Can't find any warehouse Data...!");
        }
    }





}
