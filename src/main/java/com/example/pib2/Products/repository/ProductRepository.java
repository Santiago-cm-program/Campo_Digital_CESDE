package com.example.pib2.Products.repository;

import com.example.pib2.Products.model.Entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Productos, Long>{

}
