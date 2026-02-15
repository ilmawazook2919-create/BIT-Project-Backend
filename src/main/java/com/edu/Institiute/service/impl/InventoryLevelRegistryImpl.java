package com.edu.Institiute.service.impl;
import com.edu.Institiute.dto.*;
import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
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

import java.util.ArrayList;
import java.util.Date;
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
}
