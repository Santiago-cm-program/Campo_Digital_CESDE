package com.example.pib2.Categories.repository;

import com.example.pib2.Categories.model.Entity.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Categorias, Long>{

}
