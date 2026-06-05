package com.edu.Institiute.utill.mapper;

import com.edu.Institiute.dto.SalesOrderDto;
import com.edu.Institiute.dto.SalesOrderItemDto;
import com.edu.Institiute.entity.SalesOrder;
import com.edu.Institiute.entity.SalesOrderItem;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper(componentModel = "spring")
public interface SalesOrderItemMapper {
    SalesOrderItem dtoToSalesOrderItemEntity(SalesOrderItemDto salesOrderItemDto);
    SalesOrderItemDto toSalesOrderItemDto(SalesOrderItem salesOrderItem);
}
