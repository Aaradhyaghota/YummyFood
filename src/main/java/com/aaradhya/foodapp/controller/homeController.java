package com.aaradhya.foodapp.controller;

import com.aaradhya.foodapp.dto.CustomerRequest;
import com.aaradhya.foodapp.dto.CustomerResponse;
import com.aaradhya.foodapp.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class homeController {
    private final CustomerService customerService;

    @RequestMapping("/")
    public String greet() {
        return "Hello World!";
    }


    @PostMapping("/customer/add")
    //@Valid will check that data sended is in format and constrainst wise correct
    //@RequestBody will recieve the data and not expect a page
    public ResponseEntity<String> createCustomer (@RequestBody @Valid CustomerRequest request) {
        //This is the return type of the method. The method returns a ResponseEntity object,
        // which is a Spring class used to represent the HTTP response, including status code,
        // headers, and body. Here, the body of the response is of type String.
        return ResponseEntity.ok(customerService.createCustomer(request));
    }
    //ResponseEntity.ok(...): This is a static method from the ResponseEntity class. It creates an HTTP response
    // with a status code of 200 (OK) and the body of the response is the result of the createCustomer method.
    // The result is likely a success message, ID of the newly created customer, or some confirmation.

    //get all customers
    @GetMapping("/customers")
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        List<CustomerResponse> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    //get customer by id
    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerResponse> getCustomerByID(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerByID(id));
    }

    //update customer detail
    @PutMapping("/customer/update")
    public ResponseEntity<String> updateCustomer(@RequestHeader ("Authorization") String auth, @RequestBody @Valid CustomerRequest request) throws BadRequestException {
        String email = customerService.validateAndExtractUsername(auth);
        return ResponseEntity.ok(customerService.updateCustomer(email, request));
    }

    @DeleteMapping("/customer/delete")
    public ResponseEntity<String> deleteCustomer(@RequestHeader ("Authorization") String auth) throws BadRequestException {
        String email = customerService.validateAndExtractUsername(auth);
        return ResponseEntity.ok(customerService.deleteCustomer(email));
    }
}
