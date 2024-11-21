package com.aaradhya.foodapp.controller;
import com.aaradhya.foodapp.dto.ProductRequest;
import com.aaradhya.foodapp.dto.ProductResponse;
import com.aaradhya.foodapp.service.CustomerService;
import com.aaradhya.foodapp.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class productController {
    private final ProductService productService;

    @PostMapping("/product/add")
    public ResponseEntity<String> createProduct(@RequestBody @Valid ProductRequest request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @GetMapping("/products")
    public ResponseEntity<List> getAllProducts() {
        List<ProductResponse> customers = productService.getAllProducts();
        return ResponseEntity.ok(customers);
    }

}
