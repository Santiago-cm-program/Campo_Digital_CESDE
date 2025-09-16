package com.example.pib2.Users.model.dto.Cities;

import com.example.pib2.Users.model.dto.Departaments.DepartamentsDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitiesDTO {
    private String codigoCiudad;
    private String ciudad;
    private DepartamentsDTO departamento; // objeto anidado
}
