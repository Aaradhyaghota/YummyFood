package com.aaradhya.foodapp.controller;

import com.aaradhya.foodapp.dto.ProductRequest;
import com.aaradhya.foodapp.dto.ProductResponse;
import com.aaradhya.foodapp.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponse> getProductByID(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductBYID(id));
    }

    @PutMapping("/product/update/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductRequest request) {
        return ResponseEntity.ok(productService.updateProduct(id,request));
    }

    @DeleteMapping("product/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

    @GetMapping("/products/top2")
    public ResponseEntity<List<ProductResponse>> getTop2Products(@RequestParam("minPrice") Double minPrice, @RequestParam("maxPrice") Double maxPrice) {
        // Call the service method, passing the min and max price
        return ResponseEntity.ok(productService.getTop2ProductsInPriceRange(minPrice, maxPrice));
    }
}
