package com.edu.Institiute.utill.mapper;

import com.edu.Institiute.dto.PickingListDto;
import com.edu.Institiute.entity.PickingList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Repository;

@Repository
@Mapper(componentModel = "spring", uses = {SalesOrderMapper.class, UserMapper.class, StatusMapper.class})
public interface PickingListMapper {
    @Mapping(source = "salesOrderId", target = "salesOrder")
    @Mapping(source = "shippedBy", target = "user")
    PickingList dtoToPickingListEntity(PickingListDto pickingListDto);

    @Mapping(source = "salesOrder", target = "salesOrderId")
    @Mapping(source = "user", target = "shippedBy")
    PickingListDto toPickingListDto(PickingList pickingList);
}
