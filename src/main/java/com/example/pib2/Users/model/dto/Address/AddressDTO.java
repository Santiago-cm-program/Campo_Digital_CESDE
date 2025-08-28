package com.example.pib2.Users.model.dto.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private Long idDireccion;
    private String codigoCiudad;    
    private Long idCliente;         
    private String descripcion;
}
