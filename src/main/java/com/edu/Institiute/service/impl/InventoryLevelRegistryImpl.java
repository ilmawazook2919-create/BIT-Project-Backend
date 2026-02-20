package com.edu.Institiute.service.impl;
import com.edu.Institiute.dto.*;
import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.dto.responseDto.InventoryLevelResponseDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponseInventoryLevelDto;
import com.edu.Institiute.entity.*;
import com.edu.Institiute.exception.EntryNotFoundException;
import com.edu.Institiute.repo.*;
import com.edu.Institiute.service.InventoryLevelService;
import com.edu.Institiute.utill.Generator;
import com.edu.Institiute.utill.mapper.BinMapper;
import com.edu.Institiute.utill.mapper.InventoryLevelMapper;
import com.edu.Institiute.utill.mapper.PartMapper;
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
public class InventoryLevelRegistryImpl implements InventoryLevelService {

    @Autowired
    private Generator generator;

    @Autowired
    private InventoryLevelMapper inventoryLevelMapper;

    @Autowired
    private InventoryLevelRepo inventoryLevelRepo;

    @Autowired
    private StatusRepo statusRepo;

    @Autowired
    private StatusMapper statusMapper;

    @Autowired
    private PartRepo partRepo;

    @Autowired
    private PartMapper partMapper;

    @Autowired
    private BinRepo binRepo;

    @Autowired
    private BinMapper binMapper;

    @Autowired
    private InventoryLevelService inventoryLevelService;

    @Override
    public CommonResponseDto saveInventoryLevel(RequestRegistryDto dto) {
        int invId =  generator.generateIntFourNumbers();
        Part partObj = partRepo.getPartByProvideID(dto.getPartId());
        Bin binObj = binRepo.getBinByProvideID(dto.getBinId());
        Status status = statusRepo.findStatusById(dto.getStatus())
                .orElseThrow(() -> new EntryNotFoundException("Status not found with id: " + dto.getStatus()));
        try {
            InventoryLevelDto inventoryLevelDto = new InventoryLevelDto(
                    invId,
                    partMapper.toPartDto(partObj),
                    binMapper.toBinDto(binObj),
                    dto.getQuantityOnHand(),
                    dto.getMinimumStockLevel(),
                    dto.getMaximumStockLevel(),
                    dto.getCreatedBy(),
                    new Date(),
                    0,
                    new Date(0),
                    statusMapper.toStatusDto(status)
            );
            inventoryLevelRepo.save(inventoryLevelMapper.dtoToInventoryLevelEntity(inventoryLevelDto));

            return new CommonResponseDto(201, "Inventory Level saved!", inventoryLevelDto.getId(), new ArrayList<>());
        }catch (Exception e){
            throw new EntryNotFoundException("Can't Save because of this Error -->  " + e);
        }
    }
    @Override
    public CommonResponseDto updateInventoryLevel( RequestRegistryDto dto, int inventoryLevelId) {
        try {
            Optional<Status> status = statusRepo.findStatusById(dto.getStatus());
            Part part = partRepo.getPartByProvideID(dto.getPartId());
            Bin bin = binRepo.getBinByProvideID(dto.getBinId());



            InventoryLevel inventoryLevel = inventoryLevelRepo.getInventoryLevelByProvideID(inventoryLevelId);
            inventoryLevel.setQuantityOnHand(dto.getQuantityOnHand());
            inventoryLevel.setMinimumStockLevel(dto.getMinimumStockLevel());
            inventoryLevel.setMaximumStockLevel(dto.getMaximumStockLevel());
            inventoryLevel.setCreatedBy(dto.getCreatedBy());
            inventoryLevel.setCreatedDate(dto.getCreatedDate());
            inventoryLevel.setModifyBy(dto.getModifyBy());
            inventoryLevel.setModifyDate(new Date());
            inventoryLevel.setStatus(status.get());



            inventoryLevelRepo.save(inventoryLevel);

            return new CommonResponseDto(201, "Inventory Level Updated!", inventoryLevel.getId(), new ArrayList<>());

        } catch (Exception e) {
            throw new EntryNotFoundException("Can't Update because of this Error --> " + e);
        }
    }

    @Override
    public CommonResponseDto removeInventoryLevel(int InventoryLevelId) {
        InventoryLevel InventoryLevel= inventoryLevelRepo.findByInventoryLevelId(InventoryLevelId);


        if (InventoryLevel!= null){
            inventoryLevelRepo.delete(InventoryLevel);
            return new CommonResponseDto(201, "InventoryLevel was deleted! ", true, new ArrayList<>());
        }
        else {
            throw new EntryNotFoundException("Can't find any InventoryLevel Data...!");
        }
    }
    @Override
    public PaginatedResponseInventoryLevelDto allInventoryLevel() throws SQLException {
        try {
            List<InventoryLevel> allInventoryLevelForProvidedId = inventoryLevelRepo.getAllInventoryLevel();
            List<InventoryLevelResponseDto> inventoryLevelResponseDto = new ArrayList<>();

            for (InventoryLevel r : allInventoryLevelForProvidedId) {
                inventoryLevelResponseDto.add(
                        new InventoryLevelResponseDto(

                                        r.getId(),
                                        partMapper.toPartDto(r.getPart()),
                                        binMapper.toBinDto(r.getBin()),
                                        r.getQuantityOnHand(),
                                        r.getMinimumStockLevel(),
                                        r.getMaximumStockLevel(),
                                        r.getCreatedBy(),
                                        r.getCreatedDate(),
                                        r.getModifyBy(),
                                        r.getModifyDate(),
                                        statusMapper.toStatusDto(r.getStatus())
                                )

                        );


            }

            return new PaginatedResponseInventoryLevelDto(
                    inventoryLevelRepo.count(),
                    inventoryLevelResponseDto
            );
        }catch (Exception e){
            throw new EntryNotFoundException("Can't find any data...!");
        }
    }




    @Override
    public PaginatedResponseInventoryLevelDto inventoryLevelById(int inventoryLevelId) throws SQLException {
        try {
            List<InventoryLevel> allInventoryLevelForProvidedId = inventoryLevelRepo.getAllInventoryLevelForProvidedId(inventoryLevelId);
            List<InventoryLevelResponseDto> inventoryLevelResponseDto = new ArrayList<>();

            for (InventoryLevel r : allInventoryLevelForProvidedId) {
                inventoryLevelResponseDto.add(
                        new InventoryLevelResponseDto(
                                r.getId(),
                                partMapper.toPartDto(r.getPart()),
                                binMapper.toBinDto(r.getBin()),
                                r.getQuantityOnHand(),
                                r.getMinimumStockLevel(),
                                r.getMaximumStockLevel(),
                                r.getCreatedBy(),
                                r.getCreatedDate(),
                                r.getModifyBy(),
                                r.getModifyDate(),
                                statusMapper.toStatusDto(r.getStatus())
                        )
                );
            }

            return new PaginatedResponseInventoryLevelDto(
                    inventoryLevelRepo.count(),
                    inventoryLevelResponseDto
            );
        }catch (Exception e){
            throw new EntryNotFoundException("Can't find any data for provided ID...!");
        }
    }

}