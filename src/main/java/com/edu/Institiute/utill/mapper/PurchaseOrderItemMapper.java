package com.edu.Institiute.utill.mapper;

import com.edu.Institiute.dto.PurchaseOrderItemDto;
import com.edu.Institiute.entity.PurchaseOrderItem;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper(componentModel = "spring")
public interface PurchaseOrderItemMapper {

    PurchaseOrderItem dtoToPurchaseOrderItemEntity(PurchaseOrderItemDto purchaseOrderItemDto);

    PurchaseOrderItemDto toPurchaseOrderItemDto(PurchaseOrderItem purchaseOrderItem);
}



