package com.edu.Institiute.service.impl;
import com.edu.Institiute.dto.PurchaseOrderItemDto;
import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.dto.responseDto.PurchaseOrderItemResponseDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponsePurchaseOrderItemDto;
import com.edu.Institiute.entity.*;
import com.edu.Institiute.exception.EntryNotFoundException;
import com.edu.Institiute.repo.PartRepo;
import com.edu.Institiute.repo.PurchaseOrderItemRepo;
import com.edu.Institiute.repo.PurchaseOrderRepo;
import com.edu.Institiute.repo.StatusRepo;
import com.edu.Institiute.service.PurchaseOrderItemService;
import com.edu.Institiute.utill.Generator;
import com.edu.Institiute.utill.mapper.PartMapper;
import com.edu.Institiute.utill.mapper.PurchaseOrderItemMapper;
import com.edu.Institiute.utill.mapper.PurchaseOrderMapper;
import com.edu.Institiute.utill.mapper.StatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PurchaseOrderItemImpl implements PurchaseOrderItemService {
    @Autowired
    private Generator generator;

    @Autowired
    private PurchaseOrderItemRepo purchaseOrderItemRepo;

    @Autowired
    private PurchaseOrderItemMapper purchaseOrderItemMapper;

    @Autowired
    private PurchaseOrderRepo purchaseOrderRepo;

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    @Autowired
    private PartRepo partRepo;

    @Autowired
    private PartMapper partMapper;

    @Autowired
    private StatusRepo statusRepo;

    @Autowired
    private StatusMapper statusMapper;

    @Override
    public CommonResponseDto savePurchaseOrderItem(RequestRegistryDto dto) {
        int purchaseOrderItemId =  Generator.generateIntFourNumbers();
        PurchaseOrder purchaseOrderObj = purchaseOrderRepo.getPurchaseOrderByProvideID(dto.getPurchaseOrderId());
        Part partObj = partRepo.getPartByProvideID(dto.getPartId());
        Status status = statusRepo.findStatusById(dto.getStatus())
                .orElseThrow(() -> new EntryNotFoundException("Status not found with id: " + dto.getStatus()));
        try {
            PurchaseOrderItemDto purchaseOrderItemDto = new PurchaseOrderItemDto(
                purchaseOrderItemId,
                purchaseOrderMapper.toPurchaseOrderDto(purchaseOrderObj),
                partMapper.toPartDto(partObj),
                dto.getQuantityOrdered(),
                dto.getUnitCost(),
                dto.getCreatedBy(),
                new Date(),
                0,
                new Date(0),
                statusMapper.toStatusDto(status)
        );
            System.out.println("my purchase order id " + purchaseOrderItemDto.getPurchaseOrderId());
            System.out.println("my part id " + purchaseOrderItemDto.getPartId());
            System.out.println("my status" + purchaseOrderItemDto.getStatus());

            purchaseOrderItemRepo.save(purchaseOrderItemMapper.dtoToPurchaseOrderItemEntity(purchaseOrderItemDto));

            return new CommonResponseDto(201, "Purchase Order Item saved!", purchaseOrderItemDto.getId(), new ArrayList<>());
        }catch (Exception e){
            throw new EntryNotFoundException("Can't Save because of this Error -->  " + e);
        }
    }

    @Override
    public CommonResponseDto updatePurchaseOrderItem( RequestRegistryDto dto, int purchaseOrderItemId) {
        try {
            Optional<Status> status = statusRepo.findStatusById(dto.getStatus());
            PurchaseOrder purchaseOrder = purchaseOrderRepo.getPurchaseOrderByProvideID(dto.getPurchaseOrderId());
            Part part = partRepo.getPartByProvideID(dto.getPart());


            PurchaseOrderItem purchaseOrderItem = purchaseOrderItemRepo.getPurchaseOrderItemByProvideID(purchaseOrderItemId);
            purchaseOrderItem.setQuantityOrdered(dto.getQuantityOrdered());
            purchaseOrderItem.setUnitCost(dto.getUnitCost());
            purchaseOrderItem.setCreatedBy(dto.getCreatedBy());
            purchaseOrderItem.setCreatedDate(dto.getCreatedDate());
            purchaseOrderItem.setModifyBy(dto.getModifyBy());
            purchaseOrderItem.setModifyDate(new Date());
            purchaseOrderItem.setStatus(status.get());


            return new CommonResponseDto(201, "purchaseOrderItem Updated!", purchaseOrderItem.getId(), new ArrayList<>());

        } catch (Exception e) {
            throw new EntryNotFoundException("Can't Update because of this Error --> " + e);
        }
    }
    @Override
    public CommonResponseDto removePurchaseOrderItem(int PurchaseOrderItemId) {
        PurchaseOrderItem PurchaseOrderItem= purchaseOrderItemRepo.findByPurchaseOrderItemId(PurchaseOrderItemId);


        if (PurchaseOrderItem!= null){
            purchaseOrderItemRepo.delete(PurchaseOrderItem);
            return new CommonResponseDto(201, "PurchaseOrderItem was deleted! ", true, new ArrayList<>());
        }
        else {
            throw new EntryNotFoundException("Can't find any PurchaseOrderItem Data...!");
        }
    }
    @Override
    public PaginatedResponsePurchaseOrderItemDto allPurchaseOrderItem() throws SQLException {
        try {
            List<PurchaseOrderItem> allPurchaseOrderItemForProvidedId = purchaseOrderItemRepo.getAllPurchaseOrderItem();
            List<PurchaseOrderItemResponseDto> purchaseOrderItemResponseDto = new ArrayList<>();

            for (PurchaseOrderItem r : allPurchaseOrderItemForProvidedId) {
                purchaseOrderItemResponseDto.add(
                        new PurchaseOrderItemResponseDto(

                                r.getId(),
                                purchaseOrderMapper.toPurchaseOrderDto(r.getPurchaseOrder()),
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

            return new PaginatedResponsePurchaseOrderItemDto(
                    purchaseOrderItemRepo.count(),
                    purchaseOrderItemResponseDto
            );
        }catch (Exception e){
            throw new EntryNotFoundException("Can't find any data...!");
        }
    }
    @Override
    public PaginatedResponsePurchaseOrderItemDto PurchaseOrderItemById(int purchaseOrderItemId) throws SQLException {
        try {
            List<PurchaseOrderItem> allPurchaseOrderItemForProvidedId = purchaseOrderItemRepo.getAllPurchaseOrderItemForProvidedId(purchaseOrderItemId);
            List<PurchaseOrderItemResponseDto> purchaseOrderItemResponseDto = new ArrayList<>();

            for (PurchaseOrderItem r : allPurchaseOrderItemForProvidedId) {
                purchaseOrderItemResponseDto.add(
                        new PurchaseOrderItemResponseDto(
                                r.getId(),
                                purchaseOrderMapper.toPurchaseOrderDto(r.getPurchaseOrder()),
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
            return new PaginatedResponsePurchaseOrderItemDto(
                    purchaseOrderItemRepo.count(),
                    purchaseOrderItemResponseDto
            );
        }catch (Exception e){
            throw new EntryNotFoundException("Can't find any data for provided ID...!");
        }
    }
}
