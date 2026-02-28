package com.edu.Institiute.utill.mapper;

import com.edu.Institiute.dto.BinDto;
import com.edu.Institiute.dto.PurchaseOrderDto;
import com.edu.Institiute.entity.Bin;
import com.edu.Institiute.entity.PurchaseOrder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper(componentModel = "spring")
public interface PurchaseOrderMapper {
    PurchaseOrder dtoToPurchaseOrderEntity(PurchaseOrderDto PurchaseOrderDto);

    PurchaseOrderDto toPurchaseOrderDto(PurchaseOrder purchaseOrder);
}
