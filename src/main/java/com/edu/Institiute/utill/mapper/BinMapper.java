package com.edu.Institiute.utill.mapper;
import com.edu.Institiute.dto.BinDto;
import com.edu.Institiute.entity.Bin;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper(componentModel = "spring")
public interface BinMapper {

        Bin dtoToBinEntity(BinDto binDto);

        BinDto toBinDto(Bin bin);

    }
