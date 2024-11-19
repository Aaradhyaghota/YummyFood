package com.aaradhya.foodapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(exclude = {security})
public class FoodappApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodappApplication.class, args);
//        System.out.println("Hello World!");

    }

}
