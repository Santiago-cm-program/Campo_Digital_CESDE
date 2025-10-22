package com.example.pib2.SalesDetails.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pib2.Sales.Model.Entity.Sale;
import com.example.pib2.SalesDetails.Model.Entity.SaleDetail;

public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long> {
    
    List<SaleDetail> findBySale(Sale sale);
}
