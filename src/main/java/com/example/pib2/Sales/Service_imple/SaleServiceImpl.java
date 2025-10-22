package com.example.pib2.Sales.Service_imple;

import com.example.pib2.Sales.Model.dto.SaleDTO;
import com.example.pib2.Sales.Repository.SaleRepository;
import com.example.pib2.Sales.Service.SaleService;
import com.example.pib2.SalesDetails.Model.Entity.SaleDetail;
import com.example.pib2.SalesDetails.Repository.SaleDetailRepository;
import com.example.pib2.Products.Model.Entity.Product;
import com.example.pib2.Products.Repository.ProductRepository;
import com.example.pib2.Sales.Model.Entity.Sale;
import com.example.pib2.Users.model.Entity.User.Users;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private SaleDetailRepository saleDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    private SaleDTO convertToDTO(Sale sale) {
        SaleDTO dto = new SaleDTO();
        dto.setIdVenta(sale.getId_venta());
        if (sale.getUsuario() != null) {
            dto.setUsuario(sale.getUsuario().getIdCliente());
        }
        dto.setFecha(sale.getFecha());
        dto.setTotal(sale.getTotal());
        return dto;
    }

    private Sale convertToEntity(SaleDTO dto) {
        Sale sale = new Sale();
        sale.setId_venta(dto.getIdVenta());
        Users user = new Users();
        user.setIdCliente(dto.getUsuario());
        sale.setUsuario(user);
        sale.setFecha(dto.getFecha());
        sale.setTotal(dto.getTotal());
        return sale;
    }

    @Transactional
    @Override
    public SaleDTO createSaleWithDetails(SaleDTO saleDTO) {
        Sale sale = convertToEntity(saleDTO);
        Sale savedSale = saleRepository.save(sale);
        if (saleDTO.getDetalles() != null && !saleDTO.getDetalles().isEmpty()) {
            saleDTO.getDetalles().forEach(detailDTO -> {
                SaleDetail detail = new SaleDetail();
                detail.setSale(savedSale);
                Product product = productRepository.findById(detailDTO.getProduct())
                        .orElseThrow(
                                () -> new RuntimeException("Producto no encontrado con ID: " + detailDTO.getProduct()));

                detail.setProduct(product);
                detail.setCantidad(detailDTO.getCantidad());
                saleDetailRepository.save(detail);
            });
        }
        List<SaleDetail> detallesGuardados = saleDetailRepository.findBySale(savedSale);
        List<com.example.pib2.SalesDetails.Model.dto.SaleDetailDTO> detallesDTO = detallesGuardados.stream()
                .map(detail -> {
                    com.example.pib2.SalesDetails.Model.dto.SaleDetailDTO dto = new com.example.pib2.SalesDetails.Model.dto.SaleDetailDTO();
                    dto.setId_detalleventa(detail.getId_detalleventa());
                    dto.setSale(detail.getSale().getId_venta());
                    dto.setProduct(detail.getProduct().getId_producto());
                    dto.setCantidad(detail.getCantidad());
                    return dto;
                })
                .collect(Collectors.toList());

        SaleDTO response = convertToDTO(savedSale);
        response.setDetalles(detallesDTO);

        return response;
    }

    @Override
    public List<SaleDTO> getAllSales() {
        return saleRepository.findAll()
                .stream()
                .map(sale -> {
                    SaleDTO dto = convertToDTO(sale);
                    var detalles = saleDetailRepository.findBySale(sale)
                            .stream()
                            .map(detail -> {
                                var detailDTO = new com.example.pib2.SalesDetails.Model.dto.SaleDetailDTO();
                                detailDTO.setId_detalleventa(detail.getId_detalleventa());
                                detailDTO.setSale(sale.getId_venta()); // solo ID, no entidad completa
                                detailDTO.setProduct(detail.getProduct().getId_producto());
                                detailDTO.setCantidad(detail.getCantidad());
                                return detailDTO;
                            })
                            .collect(Collectors.toList());

                    dto.setDetalles(detalles);
                    return dto;
                })
                .collect(Collectors.toList());
    }

}
