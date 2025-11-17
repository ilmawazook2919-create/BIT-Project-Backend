package com.edu.Institiute.utill.mapper;
import com.edu.Institiute.dto.PartDto;
import com.edu.Institiute.entity.Part;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper(componentModel = "spring")
public interface PartMapper {
    Part dtoToPartEntity(PartDto partDto);

    PartDto toPartDto(Part part);
}
