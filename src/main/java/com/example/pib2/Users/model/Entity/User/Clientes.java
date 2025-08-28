package com.example.pib2.Users.model.Entity.User;

import java.time.LocalDateTime;
import java.util.Date;

import com.example.pib2.Users.model.Entity.Credentials.Credenciales;
import com.example.pib2.Users.model.Entity.TypeClient.TipoClientes;
import com.example.pib2.Users.model.Entity.TypeDocument.TipoDocumento;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "Clientes")
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @ManyToOne
    @JoinColumn(name = "IdTipoCliente", referencedColumnName = "idTipoCliente", nullable = false)
    @JsonManagedReference
    private TipoClientes tipoCliente;

    @ManyToOne
    @JoinColumn(name = "IdTipoDocumento", referencedColumnName = "idTipoDocumento", nullable = false)
    @JsonManagedReference
    private TipoDocumento tipoDocumento;

    @Column(name = "NombreCompleto", length = 100, nullable = false)
    private String nombreCompleto;

    @Column(name = "Telefono", length = 50, nullable = false)
    private String telefono;

    @Column(name = "NumeroDocumento", nullable = false)
    private String numeroDocumento;
    
    @Column(name = "Activo")
    private Boolean activo;

    @Column(name = "FechaNacimiento")
    private Date fechaNacimiento;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private Credenciales credenciales;
}
