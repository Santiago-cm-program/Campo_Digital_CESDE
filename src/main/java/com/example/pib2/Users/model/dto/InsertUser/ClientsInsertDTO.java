package com.example.pib2.Users.model.dto.InsertUser;

import java.util.Date;

import com.example.pib2.Users.model.dto.Address.AddressDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientsInsertDTO {

    private Long idTipoCliente;     // llave foranea
    private Long idTipoDocumento;   // llave foranea
    private String nombreCompleto;
    private String telefono;
    private String numeroDocumento;
    private Boolean activo;
    private Date fechaNacimiento;    
    private String email;
    private String password;
    private Long idRol;             // llave foranea
    private AddressDTO direccion;   // objeto anidado
    
}
