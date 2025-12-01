package com.edu.Institiute.utill.mapper;
import com.edu.Institiute.dto.CustomerDto;
import com.edu.Institiute.entity.Customer;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer dtoToCustomerEntity(CustomerDto customerDto);

    CustomerDto toCustomerDto(Customer customer);

}
