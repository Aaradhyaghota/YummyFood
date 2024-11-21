package com.aaradhya.foodapp.mapper;


import com.aaradhya.foodapp.dto.CustomerResponse;
import com.aaradhya.foodapp.dto.ProductRequest;
import com.aaradhya.foodapp.dto.ProductResponse;
import com.aaradhya.foodapp.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    // Convert ProductRequest to Product entity
    public Product toEntity(ProductRequest request) {
        return Product.builder()
                .name(request.name())
                .price(request.price())
                .build();
    }

    //Convert Product enetity to ProductRequest record
    public ProductResponse toResponse(Product product) {
        if (product == null) {
            return null;
        }

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }
}
