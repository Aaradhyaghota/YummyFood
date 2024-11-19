package com.aaradhya.foodapp.service;


import com.aaradhya.foodapp.entity.Customer;
import com.aaradhya.foodapp.dto.CustomerRequest;
import com.aaradhya.foodapp.helper.EncryptionService;
import com.aaradhya.foodapp.mapper.CustomerMapper;
import com.aaradhya.foodapp.repo.CustomerRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    private final EncryptionService encryptionService;
    //private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public String createCustomer(@Valid CustomerRequest request) {
        Customer customer  = mapper.toEntity(request);
        customer.setPassword(encryptionService.encode(customer.getPassword()));
        //customer.setPassword(encoder.encode(customer.getPassword()));
        repo.save(customer);
        return "Created customer";
    }
}
