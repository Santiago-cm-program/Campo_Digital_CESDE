package com.example.pib2.Users.model.dto.Cities;

import com.example.pib2.Users.model.dto.Departaments.DepartamentsDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CitiesDTO {

    private String codigoCiudad;
    private String ciudad;
    @JsonIgnore
    private DepartamentsDTO departamento; // objeto anidado
}
