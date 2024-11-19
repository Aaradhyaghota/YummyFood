package com.aaradhya.foodapp.repo;

import com.aaradhya.foodapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

}
