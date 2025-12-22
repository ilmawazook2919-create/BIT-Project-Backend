package com.edu.Institiute.service.impl;

import com.edu.Institiute.dto.PartDto;
import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.requestDto.RequestTeacherDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.dto.responseDto.PartResponseDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponsePartDto;
import com.edu.Institiute.entity.Status;
import com.edu.Institiute.entity.Part;
import com.edu.Institiute.exception.EntryNotFoundException;
import com.edu.Institiute.repo.PartRepo;

import com.edu.Institiute.repo.StatusRepo;
import com.edu.Institiute.service.PartService;
import com.edu.Institiute.utill.Generator;
import com.edu.Institiute.utill.mapper.PartMapper;
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
public class PartRegistryImpl implements PartService{

    @Autowired
    private Generator generator;

    @Autowired
    private PartMapper partMapper;

    @Autowired
    private PartRepo partRepo;

    @Autowired
    private StatusRepo statusRepo;

    @Autowired
    private StatusMapper statusMapper;

    @Autowired
    private PartService partService;

   @Override
    public CommonResponseDto savePart(RequestRegistryDto dto) {
        System.out.println("Data Object :" + dto);
        try {
            int partId = generator.generateIntFourNumbers();
            Status status = statusRepo.findStatusById(dto.getStatus())
                    .orElseThrow(() -> new EntryNotFoundException("Status not found with id: " + dto.getStatus()));

            System.out.println("Data Object :" + dto);
            PartDto PartDto= new PartDto(
                    partId,
                    dto.getPartName(),
                    dto.getPartDescription(),
                    dto.getPartNumber(),
                    dto.getUnitOfMeasure(),
                    dto.getUnitCost(),
                    dto.getSellingPrice(),
                    dto.getWeight(),
                    dto.getDimensions(),
                    dto.getIsActive(),
                    dto.getPartCreatedBy(),
                    new Date(),
                    dto.getPartModifiedBy(),
                    new Date(),
                    statusMapper.toStatusDto(status)
            );

            System.out.println("Data Object 2:" + PartDto);
            partRepo.save(partMapper.dtoToPartEntity(PartDto));

            return new CommonResponseDto(201, "Part saved!", PartDto.getPartName(), new ArrayList<>());
        } catch (Exception e) {
            throw new EntryNotFoundException("Can't Save because of this Error -->  " + e);
        }
    }

    @Override
    public CommonResponseDto updatePart(RequestRegistryDto dto, String partId){
        try {
            Optional<Status> status = statusRepo.findStatusById(dto.getStatus());

            Part part = partRepo.getPartByProvideID(partId);
            part.setPartName(dto.getPartName());
            part.setPartDescription(dto.getPartDescription());
            part.setPartNumber(dto.getPartNumber());
            part.setUnitOfMeasure(dto.getUnitOfMeasure());
            part.setUnitCost(dto.getUnitCost());
            part.setSellingPrice(dto.getSellingPrice());
            part.setWeight(dto.getWeight());
            part.setDimensions(dto.getDimensions());
            part.setIsActive(dto.getIsActive());
            part.setPartCreatedBy(dto.getPartCreatedBy());
            part.setPartCreatedDate(dto.getPartCreatedDate());
            part.setPartModifyBy(dto.getPartModifiedBy());
            part.setPartModifyDate(dto.getPartModifiedDate());
            part.setStatus(status.get());

            partRepo.save(part);
            return new CommonResponseDto(201, "Part  Updated!", part.getPartName(), new ArrayList<>());
        } catch (Exception e) {
            throw new EntryNotFoundException("Can't Save because of this Error -->  " + e);
        }
    }
    @Override
    public CommonResponseDto removePart(String PartId) {
        Part Part= partRepo.findByPartId(PartId);
        if (Part != null){
            partRepo.delete(Part);
            return new CommonResponseDto(201, "Part was deleted! ", true, new ArrayList<>());
        }
        else {
            throw new EntryNotFoundException("Can't find any Part Data...!");
        }
    }
    @Override
    public PaginatedResponsePartDto allPart() throws SQLException {
        try {
            List<Part> allPart = partRepo.findAll();
            List<PartResponseDto> partResponseDtos= new ArrayList<>();

            for (Part r : allPart) {
                partResponseDtos.add(
                        new PartResponseDto(
                                r.getId(),
                                r.getPartName(),
                                r.getPartDescription(),
                                r.getPartNumber(),
                                r.getUnitOfMeasure(),
                                r.getUnitCost(),
                                r.getSellingPrice(),
                                r.getWeight(),
                                r.getDimensions(),
                                r.getIsActive(),
                                r.getPartCreatedBy(),
                                r.getPartCreatedDate(),
                                r.getPartModifyBy(),
                                r.getPartModifyDate(),
                                statusMapper.toStatusDto(r.getStatus())

                        ) );

            }

            return new PaginatedResponsePartDto(
                    partRepo.count(),
                    partResponseDtos
            );
        } catch (Exception e){
            throw new EntryNotFoundException("Can't find any data...!");
        }
    }
    @Override
    public PaginatedResponsePartDto PartById(String partId) throws SQLException {
        try {
            List<Part> allForProvidedPartId = partRepo.getAllPart(partId);
            List<PartResponseDto> partResponseArrayList = new ArrayList<>();

            for (Part r : allForProvidedPartId) {
                partResponseArrayList.add(
                        new PartResponseDto(
                                r.getId(),
                                r.getPartName(),
                                r.getPartDescription(),
                                r.getPartNumber(),
                                r.getUnitOfMeasure(),
                                r.getUnitCost(),
                                r.getSellingPrice(),
                                r.getWeight(),
                                r.getDimensions(),
                                r.getIsActive(),
                                r.getPartCreatedBy(),
                                r.getPartCreatedDate(),
                                r.getPartModifyBy(),
                                r.getPartModifyDate(),
                                statusMapper.toStatusDto(r.getStatus())

                        )
                );
            }

            return new PaginatedResponsePartDto(
                    partRepo.count(),
                    partResponseArrayList
            );
        }catch (Exception e){
            throw new EntryNotFoundException("Can't find any data for provided ID...!");
        }
    }


}
