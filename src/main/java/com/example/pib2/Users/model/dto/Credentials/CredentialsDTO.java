package com.example.pib2.Users.model.dto.Credentials;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsDTO {

    private Long idUsuario;
    private Long idCliente;     
    private String email;
    private String contrasena;   
   private LocalDateTime fechaCreacion = LocalDateTime.now();

}
