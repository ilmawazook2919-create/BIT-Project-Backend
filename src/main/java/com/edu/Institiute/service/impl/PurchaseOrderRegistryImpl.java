package com.edu.Institiute.service.impl;
import com.edu.Institiute.dto.PurchaseOrderDto;
import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.dto.responseDto.PurchaseOrderResponseDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponsePurchaseOrderDto;
import com.edu.Institiute.entity.*;
import com.edu.Institiute.exception.EntryNotFoundException;
import com.edu.Institiute.repo.PurchaseOrderRepo;
import com.edu.Institiute.repo.StatusRepo;
import com.edu.Institiute.repo.SupplierRepo;
import com.edu.Institiute.service.PurchaseOrderService;
import com.edu.Institiute.utill.Generator;
import com.edu.Institiute.utill.mapper.PurchaseOrderMapper;
import com.edu.Institiute.utill.mapper.StatusMapper;
import com.edu.Institiute.utill.mapper.SupplierMapper;
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
public class PurchaseOrderRegistryImpl implements PurchaseOrderService {

    @Autowired
    private Generator generator;

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    @Autowired
    private PurchaseOrderRepo purchaseOrderRepo;

    @Autowired
    private StatusRepo statusRepo;

    @Autowired
    private StatusMapper statusMapper;

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @Autowired
    private SupplierRepo supplierRepo;

    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public CommonResponseDto savePurchaseOrder(RequestRegistryDto dto) {
        System.out.println("Data Object :" + dto);
        try {
            int PurchaseOrderId= generator. generateIntFourNumbers();
            Status status = statusRepo.findStatusById(dto.getStatus())
                    .orElseThrow(() -> new EntryNotFoundException("Status not found with id: " + dto.getStatus()));

            System.out.println("Data Object :" + dto);
            PurchaseOrderDto purchaseOrderDto = new PurchaseOrderDto(
                    PurchaseOrderId,
                    dto.getPurchaseNumber(),
                    dto.getSupplierId(),
                    dto.getOrderDate(),
                    dto.getExpectedDeliveryDate(),
                    dto.getTotalAmount(),
                    dto.getCreatedBy(),
                    new Date(),
                    dto.getModifyBy(),
                    new Date(),
                    statusMapper.toStatusDto(status)

            );


            System.out.println("Data Object 2:" + purchaseOrderDto);
            purchaseOrderRepo.save(purchaseOrderMapper.dtoToPurchaseOrderEntity(purchaseOrderDto));

            return new CommonResponseDto(201, "PurchaseOrder saved!", purchaseOrderDto.getPurchaseNumber(), new ArrayList<>());
        } catch (Exception e) {
            throw new EntryNotFoundException("Can't Save because of this Error -->  " + e);
        }
    }
    @Override
    public CommonResponseDto updatePurchaseOrder(RequestRegistryDto dto, int purchaseOrderId) {
        try {
            Optional<Status> status = statusRepo.findStatusById(dto.getStatus());
            Supplier supplier = supplierRepo.getSupplierByProvideID(dto.getPartId());

            PurchaseOrder purchaseOrder = purchaseOrderRepo.getPurchaseOrderByProvideID(purchaseOrderId);
            purchaseOrder.setOrderDate(dto.getOrderDate());
            purchaseOrder.setExpectedDeliveryDate(dto.getExpectedDeliveryDate());
            purchaseOrder.setTotalAmount(dto.getTotalAmount());
            purchaseOrder.setCreatedBy(dto.getCreatedBy());
            purchaseOrder.setCreatedDate(dto.getCreatedDate());
            purchaseOrder.setModifyBy(dto.getModifyBy());
            purchaseOrder.setModifyDate(dto.getModifyDate());
            purchaseOrder.setStatus(status.get());

            purchaseOrderRepo.save(purchaseOrder);
            return new CommonResponseDto(201, "Purchase  Updated!", purchaseOrder.getPurchaseNumber(), new ArrayList<>());
        }catch (Exception e){
            throw new EntryNotFoundException("Can't Save because of this Error -->  " + e);
        }
    }
    @Override
    public CommonResponseDto removePurchaseOrder(int PurchaseOrderId) {
        PurchaseOrder PurchaseOrder= purchaseOrderRepo.findByPurchaseOrderId(PurchaseOrderId);


        if (PurchaseOrder!= null){
            purchaseOrderRepo.delete(PurchaseOrder);
            return new CommonResponseDto(201, "PurchaseOrder was deleted! ", true, new ArrayList<>());
        }
        else {
            throw new EntryNotFoundException("Can't find any PurchaseOrder Data...!");
        }
    }
    @Override
    public PaginatedResponsePurchaseOrderDto allPurchaseOrder() throws SQLException {
        try {
            List<PurchaseOrder> allPurchaseOrderForProvidedId = purchaseOrderRepo.getAllPurchaseOrder();
            List<PurchaseOrderResponseDto> purchaseOrderResponseDto = new ArrayList<>();

            for (PurchaseOrder r : allPurchaseOrderForProvidedId) {
                purchaseOrderResponseDto.add(
                        new PurchaseOrderResponseDto(
                                r.getId(),
                                r.getPurchaseNumber(),
                                supplierMapper.toSupplierDto(r.getSupplier()),
                                r.getOrderDate(),
                                r.getExpectedDeliveryDate(),
                                r.getTotalAmount(),
                                r.getCreatedBy(),
                                r.getCreatedDate(),
                                r.getModifyBy(),
                                r.getModifyDate(),
                                statusMapper.toStatusDto(r.getStatus())

                        )
                );
            }

            return new PaginatedResponsePurchaseOrderDto(
                    purchaseOrderRepo.count(),
                    purchaseOrderResponseDto
            );
        }catch (Exception e){
            throw new EntryNotFoundException("Can't find any data...!");
        }
    }
    @Override
    public PaginatedResponsePurchaseOrderDto PurchaseOrderById(int purchaseOrderId) throws SQLException {
        try {
            List<PurchaseOrder> allPurchaseOrderForProvidedId = purchaseOrderRepo.getAllPurchaseOrderForProvidedId(purchaseOrderId);
            List<PurchaseOrderResponseDto> purchaseOrderResponseDto = new ArrayList<>();

            for (PurchaseOrder r : allPurchaseOrderForProvidedId) {
                purchaseOrderResponseDto.add(
                        new PurchaseOrderResponseDto(
                                r.getId(),
                                r.getPurchaseNumber(),
                                supplierMapper.toSupplierDto(r.getSupplier()),
                                r.getOrderDate(),
                                r.getExpectedDeliveryDate(),
                                r.getTotalAmount(),
                                r.getCreatedBy(),
                                r.getCreatedDate(),
                                r.getModifyBy(),
                                r.getModifyDate(),
                                statusMapper.toStatusDto(r.getStatus())

                        )
                );
            }

            return new PaginatedResponsePurchaseOrderDto(
                    purchaseOrderRepo.count(),
                    purchaseOrderResponseDto
            );
        }
        catch (Exception e){
            throw new EntryNotFoundException("Can't find any data for provided ID...!");
        }
    }



}
