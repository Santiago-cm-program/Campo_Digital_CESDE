package com.example.pib2.SalesDetails.Model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleDetailDTO {

    private Long id_detalleventa;
    private Long sale;
    private Long product;
    private double cantidad;

}
