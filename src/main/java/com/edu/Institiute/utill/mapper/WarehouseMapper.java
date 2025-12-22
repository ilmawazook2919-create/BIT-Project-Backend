package com.edu.Institiute.utill.mapper;
import com.edu.Institiute.dto.WarehouseDto;
import com.edu.Institiute.entity.Warehouse;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper(componentModel = "spring")
public interface WarehouseMapper {
    Warehouse dtoToWarehouseEntity(WarehouseDto warehouseDto);

    WarehouseDto toWarehouseDto(Warehouse warehouse);

}
