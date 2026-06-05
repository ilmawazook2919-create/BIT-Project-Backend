package com.edu.Institiute.service.impl;

import com.edu.Institiute.dto.GoodsReceivedNoteItemDto;
import com.edu.Institiute.dto.SalesOrderDto;
import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.dto.responseDto.GoodsReceivedNoteItemResponseDto;
import com.edu.Institiute.dto.responseDto.SalesOrderResponseDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponseGoodsReceivedNoteItemDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponseSalesOrderDto;
import com.edu.Institiute.entity.*;
import com.edu.Institiute.exception.EntryNotFoundException;
import com.edu.Institiute.repo.*;
import com.edu.Institiute.service.SalesOrderService;
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
public class SalesOrderImpl  implements SalesOrderService{
    @Autowired
    private Generator generator;

    @Autowired
    private SalesOrderMapper salesOrderMapper;

    @Autowired
    private SalesOrderRepo salesOrderRepo;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Autowired
    private WarehouseRepo warehouseRepo;

    @Autowired
    private StatusRepo statusRepo;

    @Autowired
    private StatusMapper statusMapper;

    @Autowired
    private SalesOrderService salesOrderService;


    @Override
    public CommonResponseDto saveSalesOrder(RequestRegistryDto dto) {
              System.out.println("Data Object :" + dto);
        try {
            int salesOrderId = generator.generateIntFourNumbers();
            Customer customerObj = customerRepo.getCustomerByProvideID(dto.getCustomerId());
            Warehouse warehouseObj = warehouseRepo.getWarehouseByProvideId(dto.getWarehouseId());
            Status status = statusRepo.findStatusById(dto.getStatus())
                    .orElseThrow(() -> new EntryNotFoundException("Status not found with id: " + dto.getStatus()));

            System.out.println("Data Object :" + dto);
            SalesOrderDto SalesOrderDto= new SalesOrderDto(
                    salesOrderId,
                    dto.getOrderNumber(),
                    customerMapper.toCustomerDto(customerObj),
                    warehouseMapper.toWarehouseDto(warehouseObj),
                    dto.getOrderDate(),
                    dto.getRequiredDate(),
                    dto.getTotalAmount(),
                    dto.getCreatedBy(),
                    new Date(),
                    dto.getModifyBy(),
                    new Date(),
                    statusMapper.toStatusDto(status)
            );

            System.out.println("Data Object 2:" + SalesOrderDto);
            salesOrderRepo.save(salesOrderMapper.dtoToSalesOrderEntity(SalesOrderDto));

            return new CommonResponseDto(201, "SalesOrder saved!", SalesOrderDto.getId(), new ArrayList<>());
        } catch (Exception e) {
            throw new EntryNotFoundException("Can't Save because of this Error -->  " + e);
        }
    }
    @Override
    public CommonResponseDto updateSalesOrder( RequestRegistryDto dto, int salesOrderId) {
        try {
            Optional<Status> status = statusRepo.findStatusById(dto.getStatus());
            Customer customer = customerRepo.getCustomerByProvideID(dto.getCustomerId());
            Warehouse warehouse = warehouseRepo.getWarehouseByProvideId(dto.getWarehouseId());


            SalesOrder salesOrder = salesOrderRepo.getSalesOrderByProvideId((salesOrderId));
            salesOrder.setOrderDate(dto.getOrderDate());
            salesOrder.setRequiredDate(dto.getRequiredDate());
            salesOrder.setTotalAmount(dto.getTotalAmount());
            salesOrder.setCreatedDate(dto.getCreatedDate());
            salesOrder.setModifyBy(dto.getModifyBy());
            salesOrder.setModifyDate(new Date());
            salesOrder.setStatus(status.get());



            salesOrderRepo.save(salesOrder);

            return new CommonResponseDto(201, "SalesOrder Updated!", salesOrder.getId(), new ArrayList<>());

        } catch (Exception e) {
            throw new EntryNotFoundException("Can't Update because of this Error --> " + e);
        }
    }
    @Override
    public CommonResponseDto removeSalesOrder(int SalesOrderId) {
        SalesOrder SalesOrder= salesOrderRepo.findBySalesOrderId(SalesOrderId);


        if (SalesOrder!= null){
            salesOrderRepo.delete(SalesOrder);
            return new CommonResponseDto(201, "SalesOrder was deleted! ", true, new ArrayList<>());
        }
        else {
            throw new EntryNotFoundException("Can't find any SalesOrder Data...!");
        }
    }
    @Override
    public PaginatedResponseSalesOrderDto allSalesOrder() throws SQLException {
        try {
            List<SalesOrder> allSalesOrderForProvidedId = salesOrderRepo.getAllSalesOrder();
            List<SalesOrderResponseDto> salesOrderResponseDto = new ArrayList<>();

            for (SalesOrder r : allSalesOrderForProvidedId) {
                salesOrderResponseDto.add(
                        new SalesOrderResponseDto(
                                r.getId(),
                                r.getOrderNumber(),
                                customerMapper.toCustomerDto(r.getCustomer()),
                                warehouseMapper.toWarehouseDto(r.getWarehouse()),
                                r.getOrderDate(),
                                r.getRequiredDate(),
                                r.getTotalAmount(),
                                r.getCreatedBy(),
                                r.getCreatedDate(),
                                r.getModifyBy(),
                                r.getModifyDate(),
                                statusMapper.toStatusDto(r.getStatus())
                        )

                );


            }

            return new PaginatedResponseSalesOrderDto(
                    salesOrderRepo.count(),
                    salesOrderResponseDto
            );
        }catch (Exception e){
            throw new EntryNotFoundException("Can't find any data...!");
        }
    }




    @Override
    public PaginatedResponseSalesOrderDto SalesOrderById(int salesOrderId) throws SQLException {
        try {
            List<SalesOrder> allSalesOrderForProvidedId = salesOrderRepo.getAllSalesOrderForProvidedId(salesOrderId);
            List<SalesOrderResponseDto> salesOrderResponseDto = new ArrayList<>();

            for (SalesOrder r : allSalesOrderForProvidedId) {
                salesOrderResponseDto.add(
                        new SalesOrderResponseDto(
                                r.getId(),
                                r.getOrderNumber(),
                                customerMapper.toCustomerDto(r.getCustomer()),
                                warehouseMapper.toWarehouseDto(r.getWarehouse()),
                                r.getOrderDate(),
                                r.getRequiredDate(),
                                r.getTotalAmount(),
                                r.getCreatedBy(),
                                r.getCreatedDate(),
                                r.getModifyBy(),
                                r.getModifyDate(),
                                statusMapper.toStatusDto(r.getStatus())
                        )
                );
            }

            return new PaginatedResponseSalesOrderDto(
                    salesOrderRepo.count(),
                    salesOrderResponseDto
            );
        }catch (Exception e){
            throw new EntryNotFoundException("Can't find any data for provided ID...!");
        }
    }


}
