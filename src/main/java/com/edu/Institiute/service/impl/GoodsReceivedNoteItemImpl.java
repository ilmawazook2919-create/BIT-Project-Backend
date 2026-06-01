package com.edu.Institiute.service.impl;

import com.edu.Institiute.dto.GoodsReceivedNoteItemDto;
import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.dto.responseDto.GoodsReceivedNoteItemResponseDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponseGoodsReceivedNoteItemDto;
import com.edu.Institiute.entity.*;
import com.edu.Institiute.exception.EntryNotFoundException;
import com.edu.Institiute.repo.*;
import com.edu.Institiute.service.GoodsReceivedNoteItemService;
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
public class GoodsReceivedNoteItemImpl implements GoodsReceivedNoteItemService {
    @Autowired
    private Generator generator;

    @Autowired
    private GoodsReceivedNoteItemMapper goodsReceivedNoteItemMapper;

    @Autowired
    private GoodsReceivedNoteItemRepo goodsReceivedNoteItemRepo;

    @Autowired
    private GoodsReceivedNoteMapper goodsReceivedNoteMapper;

    @Autowired
    private GoodsReceivedNoteRepo goodsReceivedNoteRepo;

    @Autowired
    private BinRepo binRepo;

    @Autowired
    private BinMapper binMapper;

    @Autowired
    private StatusRepo statusRepo;

    @Autowired
    private StatusMapper statusMapper;

    @Autowired
    private PartRepo partRepo;

    @Autowired
    private PartMapper partMapper;


    @Autowired
    private GoodsReceivedNoteItemService goodsReceivedNoteItemService;

    @Override
    public CommonResponseDto saveGoodsReceivedNoteItem(RequestRegistryDto dto) {
        System.out.println("Data Object :" + dto);
        try {
            String goodsReceivedNoteItemId = generator.generateFourNumbers();
            GoodsReceivedNote goodsReceivedNote = goodsReceivedNoteRepo.getGoodsReceivedNoteByProvideID(dto.getGoodsReceivedNoteId());
            Part partObj = partRepo.getPartByProvideID(dto.getPartId());
            Bin binObj = binRepo.getBinByProvideID(dto.getBinId());
            Status status = statusRepo.findStatusById(dto.getStatus())
                    .orElseThrow(() -> new EntryNotFoundException("Status not found with id: " + dto.getStatus()));

            System.out.println("Data Object :" + dto);
            GoodsReceivedNoteItemDto GoodsReceivedNoteItemDto= new GoodsReceivedNoteItemDto(
                    goodsReceivedNoteItemId,
                    goodsReceivedNoteMapper.toGoodsReceivedNoteDto(goodsReceivedNote),
                    partMapper.toPartDto(partObj),
                    binMapper.toBinDto(binObj),
                    dto.getQuantityReceived(),
                    dto.getCreatedBy(),
                    new Date(),
                    dto.getModifyBy(),
                    new Date(),
                    statusMapper.toStatusDto(status)
            );

            System.out.println("Data Object 2:" + GoodsReceivedNoteItemDto);
            goodsReceivedNoteItemRepo.save(goodsReceivedNoteItemMapper.dtoToGoodsReceivedNoteItemEntity(GoodsReceivedNoteItemDto));

            return new CommonResponseDto(201, "Part saved!", GoodsReceivedNoteItemDto.getId(), new ArrayList<>());
        } catch (Exception e) {
            throw new EntryNotFoundException("Can't Save because of this Error -->  " + e);
        }
    }
    @Override
    public CommonResponseDto updateGoodsReceivedNoteItem( RequestRegistryDto dto, String goodsReceivedNoteItemId) {
        try {
            Optional<Status> status = statusRepo.findStatusById(dto.getStatus());
            GoodsReceivedNote goodsReceivedNote = goodsReceivedNoteRepo.getGoodsReceivedNoteByProvideID(dto.getGoodsReceivedNoteId());
            Part part = partRepo.getPartByProvideID(dto.getPartId());
            Bin bin = binRepo.getBinByProvideID(dto.getBinId());



            GoodsReceivedNoteItem goodsReceivedNoteItem = goodsReceivedNoteItemRepo.getGoodsReceivedNoteItemByProvideId((goodsReceivedNoteItemId));
            goodsReceivedNoteItem.setQuantityReceived(dto.getQuantityReceived());
            goodsReceivedNoteItem.setCreatedBy(dto.getCreatedBy());
            goodsReceivedNoteItem.setCreatedDate(dto.getCreatedDate());
            goodsReceivedNoteItem.setModifyBy(dto.getModifyBy());
            goodsReceivedNoteItem.setModifyDate(new Date());
            goodsReceivedNoteItem.setStatus(status.get());



            goodsReceivedNoteItemRepo.save(goodsReceivedNoteItem);

            return new CommonResponseDto(201, "goodsReceivedNote Item Updated!", goodsReceivedNoteItem.getId(), new ArrayList<>());

        } catch (Exception e) {
            throw new EntryNotFoundException("Can't Update because of this Error --> " + e);
        }
    }
    @Override
    public CommonResponseDto removeGoodsReceivedNoteItem(String GoodsReceivedNoteItemId) {
        GoodsReceivedNoteItem GoodsReceivedNoteItem= goodsReceivedNoteItemRepo.findByGoodsReceivedNoteItemId(GoodsReceivedNoteItemId);


        if (GoodsReceivedNoteItem!= null){
            goodsReceivedNoteItemRepo.delete(GoodsReceivedNoteItem);
            return new CommonResponseDto(201, "GoodsReceivedNoteItem was deleted! ", true, new ArrayList<>());
        }
        else {
            throw new EntryNotFoundException("Can't find any GoodsReceivedNoteItem Data...!");
        }
    }
    @Override
    public PaginatedResponseGoodsReceivedNoteItemDto allGoodsReceivedNoteItem() throws SQLException {
        try {
            List<GoodsReceivedNoteItem> allGoodsReceivedNoteItemForProvidedId = goodsReceivedNoteItemRepo.getAllGoodsReceivedNoteItem();
            List<GoodsReceivedNoteItemResponseDto> goodsReceivedNoteItemResponseDto = new ArrayList<>();

            for (GoodsReceivedNoteItem r : allGoodsReceivedNoteItemForProvidedId) {
                goodsReceivedNoteItemResponseDto.add(
                        new GoodsReceivedNoteItemResponseDto(

                                r.getId(),
                                goodsReceivedNoteMapper.toGoodsReceivedNoteDto(r.getGoodsReceivedNote()),
                                partMapper.toPartDto(r.getPart()),
                                binMapper.toBinDto(r.getBin()),
                                r.getQuantityReceived(),
                                r.getCreatedBy(),
                                r.getCreatedDate(),
                                r.getModifyBy(),
                                r.getModifyDate(),
                                statusMapper.toStatusDto(r.getStatus())
                        )

                );


            }

            return new PaginatedResponseGoodsReceivedNoteItemDto(
                    goodsReceivedNoteItemRepo.count(),
                    goodsReceivedNoteItemResponseDto
            );
        }catch (Exception e){
            throw new EntryNotFoundException("Can't find any data...!");
        }
    }




    @Override
    public PaginatedResponseGoodsReceivedNoteItemDto GoodsReceivedNoteItemById(String goodsReceivedNoteItemId) throws SQLException {
        try {
            List<GoodsReceivedNoteItem> allGoodsReceivedNoteItemForProvidedId = goodsReceivedNoteItemRepo.getAllGoodsReceivedNoteItemForProvidedId(goodsReceivedNoteItemId);
            List<GoodsReceivedNoteItemResponseDto> goodsReceivedNoteItemResponseDto = new ArrayList<>();

            for (GoodsReceivedNoteItem r : allGoodsReceivedNoteItemForProvidedId) {
                goodsReceivedNoteItemResponseDto.add(
                        new GoodsReceivedNoteItemResponseDto(
                                r.getId(),
                                goodsReceivedNoteMapper.toGoodsReceivedNoteDto(r.getGoodsReceivedNote()),
                                partMapper.toPartDto(r.getPart()),
                                binMapper.toBinDto(r.getBin()),
                                r.getQuantityReceived(),
                                r.getCreatedBy(),
                                r.getCreatedDate(),
                                r.getModifyBy(),
                                r.getModifyDate(),
                                statusMapper.toStatusDto(r.getStatus())
                        )
                );
            }

            return new PaginatedResponseGoodsReceivedNoteItemDto(
                    goodsReceivedNoteItemRepo.count(),
                    goodsReceivedNoteItemResponseDto
            );
        }catch (Exception e){
            throw new EntryNotFoundException("Can't find any data for provided ID...!");
        }
    }

}
