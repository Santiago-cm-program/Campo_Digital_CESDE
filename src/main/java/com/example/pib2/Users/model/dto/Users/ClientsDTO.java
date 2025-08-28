package com.example.pib2.Users.model.dto.Users;

import java.time.LocalDateTime;
import java.util.Date;

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

public class ClientsDTO {   

    private Long idCliente;
    private Long idTipoCliente;
    private String tipoClienteDescripcion;

    private Long idTipoDocumento;
    private String tipoDocumentoDescripcion;

    private String nombreCompleto;
    private String telefono;
    private String numeroDocumento;
    private Boolean activo;
    private Date fechaNacimiento;

    
}
