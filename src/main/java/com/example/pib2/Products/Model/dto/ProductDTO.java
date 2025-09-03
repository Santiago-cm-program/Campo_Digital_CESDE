package com.example.pib2.Products.Model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long idProducto;
    private String producto;
    private String descripcion;
    private String image;
    private String idUnidad;
    private Boolean isActive;
    private Long categoryId;
    private String categoryName; 
}
