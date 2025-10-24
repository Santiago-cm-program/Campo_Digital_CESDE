package com.example.pib2.SalesDetails.Service_imple;

import com.example.pib2.SalesDetails.Model.Entity.SaleDetail;
import com.example.pib2.SalesDetails.Model.dto.SaleDetailDTO;
import com.example.pib2.SalesDetails.Repository.SaleDetailRepository;
import com.example.pib2.SalesDetails.Service.SaleDetailService;
import com.example.pib2.Sales.Repository.SaleRepository;
import com.example.pib2.Products.Repository.ProductRepository;
import com.example.pib2.Sales.Model.Entity.Sale;
import com.example.pib2.Products.Model.Entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleDetailServiceImpl implements SaleDetailService {

    @Autowired
    private SaleDetailRepository saleDetailRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductRepository productRepository;

    private SaleDetailDTO convertToDTO(SaleDetail entity) {
        SaleDetailDTO dto = new SaleDetailDTO();
        dto.setId_detalleventa(entity.getId_detalleventa());
        dto.setSale(entity.getSale().getId_venta());
        dto.setProduct(entity.getProduct().getId_producto());
        dto.setCantidad(entity.getCantidad());
        return dto;
    }

    private SaleDetail convertToEntity(SaleDetailDTO dto) {
        SaleDetail entity = new SaleDetail();
        entity.setId_detalleventa(dto.getId_detalleventa());

        Sale sale = saleRepository.findById(dto.getSale())
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + dto.getSale()));

        Product product = productRepository.findById(dto.getProduct())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + dto.getProduct()));

        entity.setSale(sale);
        entity.setProduct(product);
        entity.setCantidad(dto.getCantidad());
        return entity;
    }

    @Override
    public List<SaleDetailDTO> getAllSalesDetails() {
        return saleDetailRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SaleDetailDTO createSaleDetail(SaleDetailDTO saleDetailDTO) {
        SaleDetail entity = convertToEntity(saleDetailDTO);
        SaleDetail saved = saleDetailRepository.save(entity);
        return convertToDTO(saved);
    }
}
