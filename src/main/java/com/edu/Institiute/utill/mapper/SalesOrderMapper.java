package com.edu.Institiute.utill.mapper;
import com.edu.Institiute.dto.SalesOrderDto;

import com.edu.Institiute.entity.SalesOrder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper(componentModel = "spring")
public interface SalesOrderMapper {
    SalesOrder dtoToSalesOrderEntity(SalesOrderDto salesOrderDto);
    SalesOrderDto toSalesOrderDto(SalesOrder salesOrder);
}
