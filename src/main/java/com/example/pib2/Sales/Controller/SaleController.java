package com.example.pib2.Sales.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pib2.Sales.Model.dto.SaleDTO;
import com.example.pib2.Sales.Service.SaleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@SecurityRequirement(name = "basicAuth")
@Tag(name = "Ventas", description = "Endpoints para gestionar las ventas")
@RequestMapping("/api/v1/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENTE')")
    @PostMapping("/POST/sale")
    @Operation(summary = "Crear una venta con sus detalles", description = "Crea una venta junto con todos sus detalles en una sola transacción")
    public ResponseEntity<SaleDTO> createSaleWithDetails(@RequestBody SaleDTO saleDTO) {
        SaleDTO created = saleService.createSaleWithDetails(saleDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/GET/all")
    @Operation(summary = "Obtener todas las ventas", description = "End Point para obtener todas las ventas de la base de datos")
    public ResponseEntity<List<SaleDTO>> getAllSales() {
        List<SaleDTO> sales = saleService.getAllSales();
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

}
