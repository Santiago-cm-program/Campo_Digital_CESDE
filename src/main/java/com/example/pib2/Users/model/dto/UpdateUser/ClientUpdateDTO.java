package com.example.pib2.Users.model.dto.UpdateUser;

import java.sql.Date;

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
public class ClientUpdateDTO {

    private String nombreCompleto;
    private String numeroDocumento;
    private Date fechaNacimiento;
    private String telefono;
    private String contrasena;
    private String email;    
   
}
