package com.aaradhya.foodapp.mapper;

import com.aaradhya.foodapp.dto.CustomerRequest;


import com.aaradhya.foodapp.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toEntity(CustomerRequest request) {
        return Customer.builder()
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .build();
    }
}
