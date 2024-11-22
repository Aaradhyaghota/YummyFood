package com.aaradhya.foodapp.service;

import com.aaradhya.foodapp.dto.ProductRequest;
import com.aaradhya.foodapp.dto.ProductResponse;
import com.aaradhya.foodapp.entity.Product;
import com.aaradhya.foodapp.exception.ProductNotFound;
import com.aaradhya.foodapp.mapper.ProductMapper;
import com.aaradhya.foodapp.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

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

    public ProductResponse getProductBYID(Long id) {
        return productMapper.toResponse(repo.findById(id).orElseThrow(() -> new ProductNotFound(
                        format("Cannot find Product:: No product found with the provided ID:: %d", id)
        )));
    }

    public String updateProduct(Long id, ProductRequest request) {
        Product product = repo.findById(id).orElseThrow(() -> new ProductNotFound(
                format("Cannot find Product:: No product found with the provided ID:: %d", id)
        ));
        product.setName(request.name());
        product.setPrice(request.price());
        repo.save(product);
        return "Updated prodcut with id :"+id+" succesfully";
    }

    public String deleteProduct(Long id) {
        Product product = repo.findById(id).orElseThrow(() -> new ProductNotFound(
                format("Cannot find Product:: No product found with the provided ID:: %d", id)
        ));
        repo.delete(product);
        return "Product with id: "+id+" deleted";
    }

    public List<ProductResponse> getTop2ProductsInPriceRange(Double minPrice, Double maxPrice) {
        List<Product> products = repo.findTop2ByPriceRange(minPrice, maxPrice);

        return products.stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getPrice()))
                .collect(Collectors.toList());
    }
}
