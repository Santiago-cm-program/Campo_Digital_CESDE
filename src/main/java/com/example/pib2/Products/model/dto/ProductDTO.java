package com.example.pib2.Products.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Integer productId;
    private String producto;
    private String descripcion;
    private Boolean isActive;
    private Integer unidadId;
    private Long IdCategoria;
    private String nombreCategoria;

}