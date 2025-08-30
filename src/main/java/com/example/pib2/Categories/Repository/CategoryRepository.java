package com.example.pib2.Categories.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pib2.Categories.Model.Entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
