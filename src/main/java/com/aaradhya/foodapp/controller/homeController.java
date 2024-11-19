package com.aaradhya.foodapp.controller;

import com.aaradhya.foodapp.dto.CustomerRequest;
import com.aaradhya.foodapp.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class homeController {
    private final CustomerService customerService;

    @RequestMapping("/")
    public String greed() {
        return "Hello World!";
    }

    @PostMapping("/add")
    //@Valid will check that data sended is in format and constrainst wise correct
    //@RequestBody will recieve the data and not expect a page
    public ResponseEntity<String> createCustomer (@RequestBody @Valid CustomerRequest request)
    {
//        System.out.println(request);
        return ResponseEntity.ok(customerService.createCustomer(request));
    }
}
