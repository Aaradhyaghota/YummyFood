package com.aaradhya.foodapp.service;


import com.aaradhya.foodapp.entity.Customer;
import com.aaradhya.foodapp.dto.CustomerRequest;
import com.aaradhya.foodapp.mapper.CustomerMapper;
import com.aaradhya.foodapp.repo.CustomerRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;

    public String createCustomer(@Valid CustomerRequest request) {
        Customer customer  = mapper.toEntity(request);
        repo.save(customer);
        return "Created customer";
    }
}
