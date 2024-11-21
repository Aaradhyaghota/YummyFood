package com.aaradhya.foodapp.repo;

import com.aaradhya.foodapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
