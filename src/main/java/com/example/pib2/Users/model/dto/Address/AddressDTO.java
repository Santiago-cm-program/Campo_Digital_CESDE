package com.example.pib2.Users.model.dto.Address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO {

    private String codigoCiudad; // FK hacia Ciudades
    private String descripcion; // dirección detallada

}
