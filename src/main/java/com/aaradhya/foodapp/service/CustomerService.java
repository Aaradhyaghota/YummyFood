package com.aaradhya.foodapp.service;


import com.aaradhya.foodapp.dto.LoginRequest;
import com.aaradhya.foodapp.entity.Customer;
import com.aaradhya.foodapp.dto.CustomerRequest;
import com.aaradhya.foodapp.exception.CustomerNotFound;
import com.aaradhya.foodapp.helper.EncryptionService;
import com.aaradhya.foodapp.mapper.CustomerMapper;
import com.aaradhya.foodapp.repo.CustomerRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    private final EncryptionService encryptionService;
    private final CustomerRepo customerRepo;
    //private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public String createCustomer(@Valid CustomerRequest request) {
        Customer customer  = mapper.toEntity(request);
        customer.setPassword(encryptionService.encode(customer.getPassword()));
        //customer.setPassword(encoder.encode(customer.getPassword()));
        repo.save(customer);
        return "Created customer";
    }

    public Customer getCustomer(String email) {
        return customerRepo.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFound(
                        format("Cannot update Customer:: No customer found with the provided ID:: %s", email)
                ));
    }

    public String loginCustomer(@Valid LoginRequest request) {
        Customer customer = getCustomer(request.email());
        if(!encryptionService.validates(request.password(), customer.getPassword())) {
            return "Wrong Password or Email";
        }
        return "Login successful";
    }
}
