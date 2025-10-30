package com.example.pib2.SalesDetails.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pib2.SalesDetails.Model.dto.SaleDetailDTO;
import com.example.pib2.SalesDetails.Service.SaleDetailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@SecurityRequirement(name = "basicAuth")
@Tag(name = "Detalles de Ventas", description = "Endpoints para gestionar los detalles de las ventas")
@RequestMapping("/api/v1/salesdetails")
public class SaleDetailController {

    private final SaleDetailService saleDetailService;

    public SaleDetailController(SaleDetailService saleDetailService) {
        this.saleDetailService = saleDetailService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENTE')")
    @PostMapping
    @Operation(summary="Crear un detalle de venta", description="Crea un nuevo detalle de venta en la base de datos")
    public ResponseEntity<SaleDetailDTO> createSaleDetail(@RequestBody SaleDetailDTO saleDetailDTO) {
        SaleDetailDTO created = saleDetailService.createSaleDetail(saleDetailDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    @Operation(summary="Obtener todos los detalles de ventas", description="Obtiene todos los detalles de venta con productos y ventas anidadas")
    public ResponseEntity<List<SaleDetailDTO>> getAllSalesDetails() {
        List<SaleDetailDTO> details = saleDetailService.getAllSalesDetails();
        return new ResponseEntity<>(details, HttpStatus.OK);
    }
}
