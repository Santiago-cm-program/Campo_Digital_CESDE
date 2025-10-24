package com.example.pib2.Sales.Service;

import com.example.pib2.Sales.Model.dto.SaleDTO;

import java.util.List;

public interface SaleService {

    SaleDTO createSaleWithDetails(SaleDTO saleDTO);
    List<SaleDTO> getAllSales();

}
