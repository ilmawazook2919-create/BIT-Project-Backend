package com.edu.Institiute.service.impl;
import com.edu.Institiute.dto.SalesOrderItemDto;
import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.dto.responseDto.SalesOrderItemResponseDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponseSalesOrderItemDto;
import com.edu.Institiute.entity.*;
import com.edu.Institiute.exception.EntryNotFoundException;
import com.edu.Institiute.repo.*;
import com.edu.Institiute.service.SalesOrderItemService;
import com.edu.Institiute.utill.Generator;
import com.edu.Institiute.utill.mapper.*;
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
public class SalesOrderItemImpl implements SalesOrderItemService {
    @Autowired
    private Generator generator;

    @Autowired
    private SalesOrderItemMapper salesOrderItemMapper;

    @Autowired
    private SalesOrderItemRepo salesOrderItemRepo;

    @Autowired
    private SalesOrderMapper salesOrderMapper;

    @Autowired
    private SalesOrderRepo salesOrderRepo;

    @Autowired
    private PartMapper partMapper;

    @Autowired
    private PartRepo partRepo;

    @Autowired
    private StatusRepo statusRepo;

    @Autowired
    private StatusMapper statusMapper;

    @Autowired
    private SalesOrderItemService salesOrderItemService;

    @Override
    public CommonResponseDto saveSalesOrderItem(RequestRegistryDto dto) {
        System.out.println("Data Object :" + dto);
        try {
            int salesOrderItemId = generator.generateIntFourNumbers();
            SalesOrder salesOrderObj = salesOrderRepo.getSalesOrderByProvideId(dto.getSalesOrderId());
            Part partObj = partRepo.getPartByProvideID(dto.getPartId());
            Status status = statusRepo.findStatusById(dto.getStatus())
                    .orElseThrow(() -> new EntryNotFoundException("Status not found with id: " + dto.getStatus()));

            System.out.println("Data Object :" + dto);
            SalesOrderItemDto SalesOrderItemDto= new SalesOrderItemDto(
                    salesOrderItemId,
                    salesOrderMapper.toSalesOrderDto(salesOrderObj),
                    partMapper.toPartDto(partObj),
                    dto.getQuantityOrdered(),
                    dto.getUnitCost(),
                    dto.getCreatedBy(),
                    new Date(),
                    dto.getModifyBy(),
                    new Date(),
                    statusMapper.toStatusDto(status)
            );

            System.out.println("Data Object 2:" + SalesOrderItemDto);
            salesOrderItemRepo.save(salesOrderItemMapper.dtoToSalesOrderItemEntity(SalesOrderItemDto));

            return new CommonResponseDto(201, "SalesOrderItem saved!", SalesOrderItemDto.getId(), new ArrayList<>());
        } catch (Exception e) {
            throw new EntryNotFoundException("Can't Save because of this Error -->  " + e);
        }
    }
    @Override
    public CommonResponseDto updateSalesOrderItem( RequestRegistryDto dto, int salesOrderItemId) {
        try {
            Optional<Status> status = statusRepo.findStatusById(dto.getStatus());
            SalesOrder salesOrder = salesOrderRepo.getSalesOrderByProvideId(dto.getSalesOrderId());
            Part part = partRepo.getPartByProvideID(dto.getPartId());


            SalesOrderItem salesOrderItem = salesOrderItemRepo.getSalesOrderItemByProvideId((salesOrderItemId));
            salesOrderItem.setQuantityOrdered(dto.getQuantityOrdered());
            salesOrderItem.setUnitCost(dto.getUnitCost());
            salesOrderItem.setCreatedDate(dto.getCreatedDate());
            salesOrderItem.setModifyBy(dto.getModifyBy());
            salesOrderItem.setModifyDate(new Date());
            salesOrderItem.setStatus(status.get());



            salesOrderItemRepo.save(salesOrderItem);

            return new CommonResponseDto(201, "SalesOrderItem Updated!", salesOrderItem.getId(), new ArrayList<>());

        } catch (Exception e) {
            throw new EntryNotFoundException("Can't Update because of this Error --> " + e);
        }
    }
    @Override
    public CommonResponseDto removeSalesOrderItem(int SalesOrderItemId) {
        SalesOrderItem SalesOrderItem= salesOrderItemRepo.findBySalesOrderItemId(SalesOrderItemId);


        if (SalesOrderItem!= null){
            salesOrderItemRepo.delete(SalesOrderItem);
            return new CommonResponseDto(201, "SalesOrderItem was deleted! ", true, new ArrayList<>());
        }
        else {
            throw new EntryNotFoundException("Can't find any SalesOrderItem Data...!");
        }
    }
    @Override
    public PaginatedResponseSalesOrderItemDto allSalesOrderItem() throws SQLException {
        try {
            List<SalesOrderItem> allSalesOrderItemForProvidedId = salesOrderItemRepo.getAllSalesOrderItem();
            List<SalesOrderItemResponseDto> salesOrderItemResponseDto = new ArrayList<>();

            for (SalesOrderItem r : allSalesOrderItemForProvidedId) {
                salesOrderItemResponseDto.add(
                        new SalesOrderItemResponseDto(
                                r.getId(),
                                salesOrderMapper.toSalesOrderDto(r.getSalesOrder()),
                                partMapper.toPartDto(r.getPart()),
                                r.getQuantityOrdered(),
                                r.getUnitCost(),
                                r.getCreatedBy(),
                                r.getCreatedDate(),
                                r.getModifyBy(),
                                r.getModifyDate(),
                                statusMapper.toStatusDto(r.getStatus())
                        )

                );


            }

            return new PaginatedResponseSalesOrderItemDto(
                    salesOrderItemRepo.count(),
                    salesOrderItemResponseDto
            );
        } catch (Exception e) {
            throw new EntryNotFoundException("Can't find any data...!");
        }
    }
        @Override
        public PaginatedResponseSalesOrderItemDto SalesOrderItemById ( int salesOrderItemId) throws SQLException {
            try {
                List<SalesOrderItem> allSalesOrderItemForProvidedId = salesOrderItemRepo.getAllSalesOrderItemForProvidedId(salesOrderItemId);
                List<SalesOrderItemResponseDto> salesOrderItemResponseDto = new ArrayList<>();

                for (SalesOrderItem r : allSalesOrderItemForProvidedId) {
                    salesOrderItemResponseDto.add(
                            new SalesOrderItemResponseDto(
                                    r.getId(),
                                    salesOrderMapper.toSalesOrderDto(r.getSalesOrder()),
                                    partMapper.toPartDto(r.getPart()),
                                    r.getQuantityOrdered(),
                                    r.getUnitCost(),
                                    r.getCreatedBy(),
                                    r.getCreatedDate(),
                                    r.getModifyBy(),
                                    r.getModifyDate(),
                                    statusMapper.toStatusDto(r.getStatus())
                            )
                    );
                }

                return new PaginatedResponseSalesOrderItemDto(
                        salesOrderItemRepo.count(),
                        salesOrderItemResponseDto
                );
            } catch (Exception e) {
                throw new EntryNotFoundException("Can't find any data for provided ID...!");
            }
        }


    }
