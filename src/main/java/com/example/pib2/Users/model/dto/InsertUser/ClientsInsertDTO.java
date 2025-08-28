package com.example.pib2.Users.model.dto.InsertUser;

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
public class ClientsInsertDTO {

    private Long idTipoCliente;//llave foranea
    private Long IdTipoDocumento; //llave foranea
    private String nombreCompleto;
    private String telefono;
    private String numeroDocumento;
    private Boolean activo;
    private Date fechaNacimiento;
}
