package com.aaradhya.foodapp.mapper;

import com.aaradhya.foodapp.dto.CustomerRequest;


import com.aaradhya.foodapp.dto.CustomerResponse;
import com.aaradhya.foodapp.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    // Convert CustomerRequest to Customer entity
    public Customer toEntity(CustomerRequest request) {
        return Customer.builder()
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .build();
    }

    // Convert Customer entity to CustomerResponse manually
    public CustomerResponse toReponse(Customer customer) {
        if (customer == null) {
            return null;
        }

        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getEmail()
        );
    }
}

//this by using the inbuilt mapper function
/*
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    // Convert CustomerRequest to Customer entity
    Customer toEntity(CustomerRequest request);

    // Convert Customer entity to CustomerDTO
    CustomerDTO toDTO(Customer customer);
}
 */
