package com.edu.Institiute.utill.mapper;

import com.edu.Institiute.dto.InventoryLevelDto;
import com.edu.Institiute.entity.InventoryLevel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper(componentModel = "spring")
public interface InventoryLevelMapper {
    InventoryLevel dtoToInventoryLevelEntity(InventoryLevelDto inventoryLevelDto);
    InventoryLevelDto toInventoryLevelDto(InventoryLevel inventoryLevel);
}
