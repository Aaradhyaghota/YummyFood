package com.aaradhya.foodapp.service;


import com.aaradhya.foodapp.dto.CustomerResponse;
import com.aaradhya.foodapp.dto.LoginRequest;
import com.aaradhya.foodapp.entity.Customer;
import com.aaradhya.foodapp.dto.CustomerRequest;
import com.aaradhya.foodapp.exception.CustomerNotFound;
import com.aaradhya.foodapp.helper.EncryptionService;
import com.aaradhya.foodapp.helper.JwtHelper;
import com.aaradhya.foodapp.mapper.CustomerMapper;
import com.aaradhya.foodapp.repo.CustomerRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.apache.coyote.BadRequestException;
import org.apache.tomcat.Jar;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    private final EncryptionService encryptionService;
    private final CustomerMapper customerMapper;
    private final JwtHelper jwtHelper;
    //private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public String createCustomer(@Valid CustomerRequest request) {
        Customer customer  = mapper.toEntity(request);
        customer.setPassword(encryptionService.encode(customer.getPassword()));
        //customer.setPassword(encoder.encode(customer.getPassword()));
        repo.save(customer);
        return "Created customer";
    }

    //getting all customer by stream api
    public List<CustomerResponse> getAllCustomers() {
        return repo.findAll().stream()
                .map(customerMapper::toReponse)
                .collect(Collectors.toList());
    }

    public CustomerResponse getCustomerByID(long id) {
        return customerMapper.toReponse(repo.findById(id).get());
    }

    public Customer getCustomerByEmail(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFound(
                        format("Cannot update Customer:: No customer found with the provided ID:: %s", email)
                ));
    }

    public String loginCustomer(LoginRequest request) {
        Customer customer = getCustomerByEmail(request.email());
        if(!encryptionService.validates(request.password(), customer.getPassword())) {
            return "Wrong Password or Email";
        }
        return jwtHelper.generateToken(request.email());
    }

    public String validateAndExtractUsername(String auth) throws BadRequestException {
        if(auth == null || !auth.startsWith("Bearer ")){
            throw new BadRequestException("Invalid Authorization header");
        }
        String token = auth.replace("Bearer ", "").trim();

        if (!jwtHelper.validateToken(token)) {
            throw new BadRequestException("Invalid JWT token");
        }

        return jwtHelper.extractUsername(token);
    }

    public String updateCustomer(String email, CustomerRequest request) {
        Customer customer = getCustomerByEmail(email);

        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setPassword(encryptionService.encode(request.password()));
        repo.save(customer);
        return "Customer details Updated";
    }

    public String deleteCustomer(String email){
        Customer customer = getCustomerByEmail(email);
        repo.delete(customer);
        return "Deleted "+ email +"customer";
    }
}
