package com.example.pib2.Users.model.dto.Departaments;

import java.util.List;

import com.example.pib2.Users.model.dto.Cities.CitiesDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartamentsDTO {
    private String codigoDepartamento;
    private String departamento;
    private List<CitiesDTO> ciudades;
}
