package com.edu.Institiute.service.impl;

import com.edu.Institiute.dto.GoodsReceivedNoteDto;
import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.dto.responseDto.GoodsReceivedNoteResponseDto;
import com.edu.Institiute.dto.responseDto.InventoryLevelResponseDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponseGoodsReceivedNoteDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponseInventoryLevelDto;
import com.edu.Institiute.entity.*;
import com.edu.Institiute.exception.EntryNotFoundException;
import com.edu.Institiute.repo.GoodsReceivedNoteRepo;
import com.edu.Institiute.repo.PurchaseOrderRepo;
import com.edu.Institiute.repo.StatusRepo;
import com.edu.Institiute.repo.UserRepo;
import com.edu.Institiute.service.GoodsReceivedNoteService;
import com.edu.Institiute.utill.Generator;
import com.edu.Institiute.utill.mapper.GoodsReceivedNoteMapper;
import com.edu.Institiute.utill.mapper.PurchaseOrderMapper;
import com.edu.Institiute.utill.mapper.StatusMapper;
import com.edu.Institiute.utill.mapper.UserMapper;
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
public class GoodsReceivedNoteImpl implements GoodsReceivedNoteService {
    @Autowired
    private Generator generator;

    @Autowired
    private GoodsReceivedNoteMapper goodsReceivedNoteMapper;

    @Autowired
    private GoodsReceivedNoteRepo goodsReceivedNoteRepo;

    @Autowired
    private StatusRepo statusRepo;

    @Autowired
    private StatusMapper statusMapper;

    @Autowired
    private PurchaseOrderRepo purchaseOrderRepo;

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;


    @Autowired
    private  GoodsReceivedNoteService goodsReceivedNoteService;

    @Override
    public CommonResponseDto saveGoodsReceivedNote(RequestRegistryDto dto) {
        System.out.println("Data Object :" + dto);
        try {
            String goodsReceivedNoteId = generator.generateFourNumbers();
            PurchaseOrder purchaseOrder = purchaseOrderRepo.getPurchaseOrderByProvideID(dto.getPurchaseOrderId());
            Status status = statusRepo.findStatusById(dto.getStatus())
                    .orElseThrow(() -> new EntryNotFoundException("Status not found with id: " + dto.getStatus()));

            System.out.println("Data Object :" + dto);
            GoodsReceivedNoteDto GoodsReceivedNoteDto= new GoodsReceivedNoteDto(
                    goodsReceivedNoteId,
                    purchaseOrderMapper.toPurchaseOrderDto(purchaseOrder),
                    dto.getReceiptDate(),
                    dto.getCreatedBy(),
                    new Date(),
                    dto.getModifyBy(),
                    new Date(),
                    statusMapper.toStatusDto(status)
            );

            System.out.println("Data Object 2:" + GoodsReceivedNoteDto);
            goodsReceivedNoteRepo.save(goodsReceivedNoteMapper.dtoToGoodsReceivedNoteEntity(GoodsReceivedNoteDto));

            return new CommonResponseDto(201, "Part saved!", GoodsReceivedNoteDto.getId(), new ArrayList<>());
        } catch (Exception e) {
            throw new EntryNotFoundException("Can't Save because of this Error -->  " + e);
        }
    }
    @Override
    public CommonResponseDto updateGoodsReceivedNote( RequestRegistryDto dto, String goodsReceivedNoteId) {

        try {
            Optional<Status> status = statusRepo.findStatusById(dto.getStatus());
            PurchaseOrder purchaseOrder = purchaseOrderRepo.getPurchaseOrderByProvideID(dto.getPurchaseOrderId());



            GoodsReceivedNote goodsReceivedNote = goodsReceivedNoteRepo.getGoodsReceivedNoteByProvideID(goodsReceivedNoteId);
            goodsReceivedNote.setReceiptDate(dto.getReceiptDate());

            goodsReceivedNote.setCreatedBy(dto.getCreatedBy());
            goodsReceivedNote.setCreatedDate(dto.getCreatedDate());
            goodsReceivedNote.setModifyBy(dto.getModifyBy());
            goodsReceivedNote.setModifyDate(new Date());
            goodsReceivedNote.setStatus(status.get());



            goodsReceivedNoteRepo.save(goodsReceivedNote);

            return new CommonResponseDto(201, "goodsReceivedNote Updated!", goodsReceivedNote.getId(), new ArrayList<>());

        } catch (Exception e) {
            throw new EntryNotFoundException("Can't Update because of this Error --> " + e);
        }
    }
    @Override
    public CommonResponseDto removeGoodsReceivedNote(String GoodsReceivedNoteId) {
        GoodsReceivedNote GoodsReceivedNote= goodsReceivedNoteRepo.findByGoodsReceivedNoteId(GoodsReceivedNoteId);


        if (GoodsReceivedNote!= null){
            goodsReceivedNoteRepo.delete(GoodsReceivedNote);
            return new CommonResponseDto(201, "GoodsReceivedNote was deleted! ", true, new ArrayList<>());
        }
        else {
            throw new EntryNotFoundException("Can't find any GoodsReceivedNote Data...!");
        }
    }
    @Override
    public PaginatedResponseGoodsReceivedNoteDto allGoodsReceivedNote() throws SQLException {
        try {
            List<GoodsReceivedNote> allGoodsReceivedNoteForProvidedId = goodsReceivedNoteRepo.getAllGoodsReceivedNote();
            List<GoodsReceivedNoteResponseDto> goodsReceivedNoteResponseDto = new ArrayList<>();

            for (GoodsReceivedNote r : allGoodsReceivedNoteForProvidedId) {
                goodsReceivedNoteResponseDto.add(
                        new GoodsReceivedNoteResponseDto(

                                r.getId(),
                                purchaseOrderMapper.toPurchaseOrderDto(r.getPurchaseOrder()),
                                r.getReceiptDate(),
                                r.getCreatedBy(),
                                r.getCreatedDate(),
                                r.getModifyBy(),
                                r.getModifyDate(),
                                statusMapper.toStatusDto(r.getStatus())
                        )

                );


            }

            return new PaginatedResponseGoodsReceivedNoteDto(
                    goodsReceivedNoteRepo.count(),
                    goodsReceivedNoteResponseDto
            );
        }catch (Exception e){
            throw new EntryNotFoundException("Can't find any data...!");
        }
    }

    @Override
    public PaginatedResponseGoodsReceivedNoteDto GoodsReceivedNoteById(String goodsReceivedNoteId) throws SQLException {
        try {
            List<GoodsReceivedNote> allGoodsReceivedNoteForProvidedId = goodsReceivedNoteRepo.getAllGoodsReceivedNoteForProvidedId(goodsReceivedNoteId);
            List<GoodsReceivedNoteResponseDto> goodsReceivedNoteResponseDto = new ArrayList<>();

            for (GoodsReceivedNote r : allGoodsReceivedNoteForProvidedId) {
                goodsReceivedNoteResponseDto.add(
                        new GoodsReceivedNoteResponseDto(
                                r.getId(),
                                purchaseOrderMapper.toPurchaseOrderDto(r.getPurchaseOrder()),
                                r.getReceiptDate(),
                                r.getCreatedBy(),
                                r.getCreatedDate(),
                                r.getModifyBy(),
                                r.getModifyDate(),
                                statusMapper.toStatusDto(r.getStatus())
                        )
                );
            }

            return new PaginatedResponseGoodsReceivedNoteDto(
                    goodsReceivedNoteRepo.count(),
                    goodsReceivedNoteResponseDto
            );
        }catch (Exception e){
            throw new EntryNotFoundException("Can't find any data for provided ID...!");
        }
    }




}


