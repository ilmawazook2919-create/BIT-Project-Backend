package com.edu.Institiute.service.impl;

import com.edu.Institiute.dto.PickingListDto;

import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.dto.responseDto.PickingListResponseDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponsePickingListDto;
import com.edu.Institiute.entity.*;
import com.edu.Institiute.exception.EntryNotFoundException;
import com.edu.Institiute.repo.*;
import com.edu.Institiute.service.PickingListService;
import com.edu.Institiute.utill.Generator;
import com.edu.Institiute.utill.mapper.PickingListMapper;
import com.edu.Institiute.utill.mapper.SalesOrderMapper;
import com.edu.Institiute.utill.mapper.StatusMapper;
import com.edu.Institiute.utill.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PickingListImpl  implements PickingListService {
    @Autowired
    private Generator generator;

    @Autowired
    private PickingListMapper pickingListMapper;

    @Autowired
    private PickingListRepo pickingListRepo;

    @Autowired
    private SalesOrderRepo salesOrderRepo;

    @Autowired
    private SalesOrderMapper salesOrderMapper;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StatusRepo statusRepo;

    @Autowired
    private StatusMapper statusMapper;

    @Override
    public CommonResponseDto savePickingList(RequestRegistryDto dto) {
        System.out.println("Data Object :" + dto);
        try {
            int pickingListId = generator.generateIntFourNumbers();
            SalesOrder salesOrderObj = salesOrderRepo.findById(dto.getSalesOrderId())
                    .orElseThrow(() -> new EntryNotFoundException("Sales order not found with id: " + dto.getSalesOrderId()));
            User userObj = userRepo.findById(dto.getShippedBy())
                    .orElseThrow(() -> new EntryNotFoundException("User not found with id: " + dto.getShippedBy()));
            Status status = statusRepo.findStatusById(dto.getStatus())
                    .orElseThrow(() -> new EntryNotFoundException("Status not found with id: " + dto.getStatus()));

            System.out.println("Data Object :" + userObj);
            PickingListDto PickingListDto= new PickingListDto(
                    pickingListId,
                    salesOrderMapper.toSalesOrderDto(salesOrderObj),
                    dto.getShipmentDate(),
                    userMapper.toUserDto(userObj),
                    dto.getTrackingNumber(),
                    dto.getCreatedBy(),
                    new Date(),
                    0,
                    new Date(0),
                    statusMapper.toStatusDto(status)
            );

            System.out.println("Data Object 2:" + PickingListDto);
            pickingListRepo.save(pickingListMapper.dtoToPickingListEntity(PickingListDto));

            return new CommonResponseDto(201, "pickingList saved!", PickingListDto.getId(), new ArrayList<>());
        } catch (Exception e) {
            throw new EntryNotFoundException("Can't Save because of this Error -->  " + e);
        }
    }
    @Override
    public CommonResponseDto updatePickingList( RequestRegistryDto dto, int pickingListId) {
        try {
            Status status = statusRepo.findStatusById(dto.getStatus())
                    .orElseThrow(() -> new EntryNotFoundException("Status not found with id: " + dto.getStatus()));
            SalesOrder salesOrder = salesOrderRepo.findById(dto.getSalesOrderId())
                    .orElseThrow(() -> new EntryNotFoundException("Sales order not found with id: " + dto.getSalesOrderId()));
            User user = userRepo.findById(dto.getShippedBy())
                    .orElseThrow(() -> new EntryNotFoundException("User not found with id: " + dto.getShippedBy()));

            PickingList pickingList = pickingListRepo.findById(pickingListId)
                    .orElseThrow(() -> new EntryNotFoundException("Can't find any pickingList Data...!"));
            pickingList.setSalesOrder(salesOrder);
            pickingList.setShipmentDate(dto.getShipmentDate());
            pickingList.setUser(user);
            pickingList.setTrackingNumber(dto.getTrackingNumber());
            pickingList.setCreatedBy(dto.getCreatedBy());
            pickingList.setCreatedDate(dto.getCreatedDate());
            pickingList.setModifyBy(dto.getModifyBy());
            pickingList.setModifyDate(new Date());
            pickingList.setStatus(status);

            pickingListRepo.save(pickingList);

            return new CommonResponseDto(201, "pickingList Updated!", pickingList.getId(), new ArrayList<>());

        } catch (Exception e) {
            throw new EntryNotFoundException("Can't Update because of this Error --> " + e);
        }
    }
    @Override
    public CommonResponseDto removePickingList(int PickingListId) {
        PickingList PickingList = pickingListRepo.findById(PickingListId).orElse(null);


        if (PickingList!= null){
            pickingListRepo.delete(PickingList);
            return new CommonResponseDto(201, "PickingList was deleted! ", true, new ArrayList<>());
        }
        else {
            throw new EntryNotFoundException("Can't find any pickingList Data...!");
        }
    }
    @Override
    public PaginatedResponsePickingListDto allPickingList() throws SQLException {
        try {
            List<PickingList> allPickingListForProvidedId = pickingListRepo.getAllPickingList();
            List<PickingListResponseDto> pickingListResponseDto = new ArrayList<>();

            for (PickingList r : allPickingListForProvidedId) {
                pickingListResponseDto.add(
                        new PickingListResponseDto(
                                r.getId(),
                                salesOrderMapper.toSalesOrderDto(r.getSalesOrder()),
                                r.getShipmentDate(),
                                userMapper.toUserDto(r.getUser()),
                                r.getTrackingNumber(),
                                r.getCreatedBy(),
                                r.getCreatedDate(),
                                r.getModifyBy(),
                                r.getModifyDate(),
                                statusMapper.toStatusDto(r.getStatus())
                        )

                );


            }

            return new PaginatedResponsePickingListDto(
                    pickingListRepo.count(),
                    pickingListResponseDto
            );
        }catch (Exception e){
            throw new EntryNotFoundException("Can't find any data...!");
        }
    }




    @Override
    public PaginatedResponsePickingListDto PickingListById(int pickingListId) throws SQLException {
        try {
            PickingList r = pickingListRepo.findById(pickingListId)
                    .orElseThrow(() -> new EntryNotFoundException("Can't find any data for provided ID...!"));
            List<PickingListResponseDto> pickingListResponseDto = new ArrayList<>();

            pickingListResponseDto.add(
                    new PickingListResponseDto(
                            r.getId(),
                            salesOrderMapper.toSalesOrderDto(r.getSalesOrder()),
                            r.getShipmentDate(),
                            userMapper.toUserDto(r.getUser()),
                            r.getTrackingNumber(),
                            r.getCreatedBy(),
                            r.getCreatedDate(),
                            r.getModifyBy(),
                            r.getModifyDate(),
                            statusMapper.toStatusDto(r.getStatus())
                    )
            );

            return new PaginatedResponsePickingListDto(
                    pickingListRepo.count(),
                    pickingListResponseDto
            );
        }catch (Exception e){
            throw new EntryNotFoundException("Can't find any data for provided ID...!");
        }
    }


}




