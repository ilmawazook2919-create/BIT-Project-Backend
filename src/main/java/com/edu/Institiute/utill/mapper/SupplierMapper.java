package com.edu.Institiute.utill.mapper;
import com.edu.Institiute.dto.SupplierDto;
import com.edu.Institiute.entity.Supplier;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;


@Repository
@Mapper(componentModel = "spring")
public interface SupplierMapper {
    Supplier dtoToSupplierEntity(SupplierDto supplierDto);

    SupplierDto toSupplierDto(Supplier supplier);


}
