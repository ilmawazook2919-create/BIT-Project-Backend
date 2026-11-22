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

    @Autowired
    private InventoryLevelRepo inventoryLevelRepo;

    @Override
    public CommonResponseDto saveGoodsReceivedNoteItem(RequestRegistryDto dto) {
        System.out.println("Data Object :" + dto);
        try {
            String goodsReceivedNoteItemId = generator.generateFourNumbers();
            GoodsReceivedNote goodsReceivedNote = goodsReceivedNoteRepo.getGoodsReceivedNoteByProvideID(dto.getGoodsReceivedNoteId());
            Part partObj = partRepo.getPartByProvideID(dto.getPartId());
            Bin binObj = binRepo.getBinByProvideID(dto.getBinId());
            if (goodsReceivedNote == null) {
                throw new EntryNotFoundException("Goods received note not found with id: " + dto.getGoodsReceivedNoteId());
            }
            if (partObj == null) {
                throw new EntryNotFoundException("Part not found with id: " + dto.getPartId());
            }
            if (binObj == null) {
                throw new EntryNotFoundException("Bin not found with id: " + dto.getBinId());
            }
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

            GoodsReceivedNoteItem goodsReceivedNoteItem = goodsReceivedNoteItemMapper.dtoToGoodsReceivedNoteItemEntity(GoodsReceivedNoteItemDto);
            goodsReceivedNoteItem.setGoodsReceivedNote(goodsReceivedNote);
            goodsReceivedNoteItem.setPart(partObj);
            goodsReceivedNoteItem.setBin(binObj);
            goodsReceivedNoteItem.setStatus(status);
            goodsReceivedNoteItem.setQuantityReceived(dto.getQuantityReceived());
            goodsReceivedNoteItemRepo.save(goodsReceivedNoteItem);
            applyStockChange(partObj, binObj, toIntQty(dto.getQuantityReceived()), status, dto.getCreatedBy());

            return new CommonResponseDto(201, "Goods received note item saved!", goodsReceivedNoteItem.getId(), new ArrayList<>());
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
            int oldQty = toIntQty(goodsReceivedNoteItem.getQuantityReceived());
            Part oldPart = goodsReceivedNoteItem.getPart();
            Bin oldBin = goodsReceivedNoteItem.getBin();
            int newQty = toIntQty(dto.getQuantityReceived());

            goodsReceivedNoteItem.setGoodsReceivedNote(goodsReceivedNote);
            goodsReceivedNoteItem.setPart(part);
            goodsReceivedNoteItem.setBin(bin);
            goodsReceivedNoteItem.setQuantityReceived(dto.getQuantityReceived());
            goodsReceivedNoteItem.setCreatedBy(dto.getCreatedBy());
            goodsReceivedNoteItem.setCreatedDate(dto.getCreatedDate());
            goodsReceivedNoteItem.setModifyBy(dto.getModifyBy());
            goodsReceivedNoteItem.setModifyDate(new Date());
            goodsReceivedNoteItem.setStatus(status.get());

            boolean sameLocation = oldPart != null && oldBin != null && part != null && bin != null
                    && oldPart.getId() == part.getId()
                    && oldBin.getId().equals(bin.getId());
            if (sameLocation) {
                applyStockChange(part, bin, newQty - oldQty, status.get(), dto.getModifyBy());
            } else {
                applyStockChange(oldPart, oldBin, -oldQty, status.get(), dto.getModifyBy());
                applyStockChange(part, bin, newQty, status.get(), dto.getModifyBy());
            }

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
            applyStockChange(
                    GoodsReceivedNoteItem.getPart(),
                    GoodsReceivedNoteItem.getBin(),
                    -toIntQty(GoodsReceivedNoteItem.getQuantityReceived()),
                    GoodsReceivedNoteItem.getStatus(),
                    GoodsReceivedNoteItem.getModifyBy()
            );
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

    private void applyStockChange(Part part, Bin bin, int delta, Status status, int userId) {
        if (part == null || bin == null || delta == 0) {
            return;
        }
        InventoryLevel level = inventoryLevelRepo.findByPartAndBin(part.getId(), bin.getId());
        if (level == null) {
            InventoryLevel created = new InventoryLevel(
                    generator.generateIntFourNumbers(),
                    Math.max(delta, 0),
                    0,
                    0,
                    userId,
                    new Date(),
                    0,
                    new Date(0),
                    part,
                    bin,
                    status
            );
            inventoryLevelRepo.save(created);
            return;
        }
        int next = level.getQuantityOnHand() + delta;
        level.setQuantityOnHand(Math.max(next, 0));
        level.setModifyBy(userId);
        level.setModifyDate(new Date());
        if (status != null) {
            level.setStatus(status);
        }
        inventoryLevelRepo.save(level);
    }

    private int toIntQty(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0;
        }
        return (int) Double.parseDouble(value.trim());
    }

}
