package com.edu.Institiute.utill.mapper;

import com.edu.Institiute.dto.PickingListDto;
import com.edu.Institiute.dto.SalesOrderItemDto;
import com.edu.Institiute.entity.PickingList;
import com.edu.Institiute.entity.SalesOrderItem;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper(componentModel = "spring")
public interface PickingListMapper {
    PickingList dtoToPickingListEntity(PickingListDto pickingListDto);
    PickingListDto toPickingListDto(PickingList pickingList);
}
