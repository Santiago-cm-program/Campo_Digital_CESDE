package com.example.pib2.Sales.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pib2.Sales.Model.Entity.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

}
