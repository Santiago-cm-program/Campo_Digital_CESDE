package com.example.pib2.Products.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pib2.Products.Model.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
