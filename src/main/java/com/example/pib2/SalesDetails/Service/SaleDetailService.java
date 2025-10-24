package com.example.pib2.SalesDetails.Service;

import com.example.pib2.SalesDetails.Model.dto.SaleDetailDTO;

import java.util.List;

public interface SaleDetailService {

    List<SaleDetailDTO> getAllSalesDetails();
    SaleDetailDTO createSaleDetail(SaleDetailDTO saleDetailDTO);

}
