package com.example.pib2.Sales.Model.dto;

import java.time.LocalDate;
import java.util.List;

import com.example.pib2.SalesDetails.Model.dto.SaleDetailDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleDTO {

    private Long idVenta;
    private Long usuario;
    private LocalDate fecha;
    private double total;
    private List<SaleDetailDTO> detalles;

}
