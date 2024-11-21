package com.aaradhya.foodapp.service;

import com.aaradhya.foodapp.dto.ProductRequest;
import com.aaradhya.foodapp.dto.ProductResponse;
import com.aaradhya.foodapp.entity.Product;
import com.aaradhya.foodapp.mapper.ProductMapper;
import com.aaradhya.foodapp.repo.ProductRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo repo;
    private final ProductMapper mapper;
    private final ProductMapper productMapper;


    public String createProduct(ProductRequest request) {
        Product product = mapper.toEntity(request);
        repo.save(product);
        return "Product created";
    }

    public List<ProductResponse> getAllProducts() {
        return repo.findAll().stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }
}
