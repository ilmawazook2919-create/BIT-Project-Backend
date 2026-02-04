package com.edu.Institiute.service.impl;
import com.edu.Institiute.dto.BinDto;
import com.edu.Institiute.dto.requestDto.RequestRegistryDto;
import com.edu.Institiute.dto.responseDto.BinResponseDto;
import com.edu.Institiute.dto.responseDto.paginated.PaginatedResponseBinDto;
import com.edu.Institiute.dto.responseDto.CommonResponseDto;
import com.edu.Institiute.entity.Bin;
import com.edu.Institiute.entity.Status;
import com.edu.Institiute.exception.EntryNotFoundException;
import com.edu.Institiute.repo.BinRepo;
import com.edu.Institiute.repo.StatusRepo;
import com.edu.Institiute.service.BinService;
import com.edu.Institiute.utill.Generator;
import com.edu.Institiute.utill.mapper.BinMapper;
import com.edu.Institiute.utill.mapper.StatusMapper;
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
public class BinRegistryImpl implements BinService {
    @Autowired
    private Generator generator;

    @Autowired
    private BinMapper binMapper;

    @Autowired
    private BinRepo binRepo;

    @Autowired
    private StatusRepo statusRepo;

    @Autowired
    private StatusMapper statusMapper;

    @Autowired
    private BinService binService;


    @Override
    public CommonResponseDto saveBin(RequestRegistryDto dto) {
        System.out.println("Data Object :" + dto);
        try {
            String BinId= generator.generateFourNumbers();
            Status status = statusRepo.findStatusById(dto.getStatus())
                    .orElseThrow(() -> new EntryNotFoundException("Status not found with id: " + dto.getStatus()));

            System.out.println("Data Object :" + dto);
            BinDto binDto = new BinDto(
                    BinId,
                    dto.getBinCode(),
                    dto.getBinDescription(),
                    dto.getBinCreatedBy(),
                    new Date(),
                    dto.getBinModifiedBy(),
                    new Date(),
                    statusMapper.toStatusDto(status)

            );

            System.out.println("Data Object 2:" + binDto);
            binRepo.save(binMapper.dtoToBinEntity(binDto));

            return new CommonResponseDto(201, "Bin saved!", binDto.getBinCode(), new ArrayList<>());
        } catch (Exception e) {
            throw new EntryNotFoundException("Can't Save because of this Error -->  " + e);
        }
    }
    @Override
    public CommonResponseDto updateBin(RequestRegistryDto dto, String binId) {
        try {
            Optional<Status> status = statusRepo.findStatusById(dto.getStatus());

            Bin bin = binRepo.getBinByProvideID(binId);
            bin.setBinCode(dto.getBinCode());
            bin.setBinDescription(dto.getBinDescription());
            bin.setBinCreatedBy(dto.getBinCreatedBy());
            bin.setBinCreatedDate(dto.getBinCreatedDate());
            bin.setBinModifiedBy(dto.getBinModifiedBy());
            bin.setBinModifiedDate(dto.getBinModifiedDate());
            bin.setStatus(status.get());

            binRepo.save(bin);
            return new CommonResponseDto(201, "Bin  Updated!", bin.getBinCode(), new ArrayList<>());
        }catch (Exception e){
            throw new EntryNotFoundException("Can't Save because of this Error -->  " + e);
        }
    }

    @Override
    public CommonResponseDto removeBin(String BinId) {
        Bin Bin= binRepo.findByBinId(BinId);


        if (Bin!= null){
            binRepo.delete(Bin);
            return new CommonResponseDto(201, "Bin was deleted! ", true, new ArrayList<>());
        }
        else {
            throw new EntryNotFoundException("Can't find any Bin Data...!");
        }
    }

    @Override
    public PaginatedResponseBinDto allBin() throws SQLException {
        try {
            List<Bin> allBinForProvidedId = binRepo.getAllBins();
            List<BinResponseDto> binResponseDto = new ArrayList<>();

            for (Bin r : allBinForProvidedId) {
                binResponseDto.add(
                        new BinResponseDto(
                                r.getId(),
                                r.getBinCode(),
                                r.getBinDescription(),
                                r.getBinCreatedBy(),
                                r.getBinCreatedDate(),
                                r.getBinModifiedBy(),
                                r.getBinModifiedDate(),
                                statusMapper.toStatusDto(r.getStatus())

                        )
                );
            }

            return new PaginatedResponseBinDto(
                    binRepo.count(),
                    binResponseDto
            );
        }catch (Exception e){
            throw new EntryNotFoundException("Can't find any data...!");
        }
    }

    @Override
    public PaginatedResponseBinDto BinById(String binId) throws SQLException {
        try {
            List<Bin> allForProvidedBinId = binRepo.getAllBin(binId);
            List<BinResponseDto> binResponseArrayList = new ArrayList<>();

            for (Bin r : allForProvidedBinId) {
                binResponseArrayList.add(
                        new BinResponseDto(
                                r.getId(),
                                r.getBinCode(),
                                r.getBinDescription(),
                                r.getBinCreatedBy(),
                                r.getBinCreatedDate(),
                                r.getBinModifiedBy(),
                                r.getBinModifiedDate(),
                                statusMapper.toStatusDto(r.getStatus())

                        )
                );
            }

            return new PaginatedResponseBinDto(
                    binRepo.count(),
                    binResponseArrayList
            );
        }
        catch (Exception e){
            throw new EntryNotFoundException("Can't find any data for provided ID...!");
        }
    }


}

