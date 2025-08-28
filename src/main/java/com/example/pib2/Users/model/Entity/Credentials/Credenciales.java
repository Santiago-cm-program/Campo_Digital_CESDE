package com.example.pib2.Users.model.Entity.Credentials;
import java.time.LocalDateTime;

import com.example.pib2.Users.model.Entity.User.Clientes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credenciales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdUsuario")
    private Long idUsuario;

    @OneToOne
    @JoinColumn(name = "IdCliente", referencedColumnName = "idCliente", nullable = false)
    private Clientes cliente;

    @Column(name = "Email", nullable = false, length = 255)
    private String email;

    @Column(name = "Contraseña", nullable = false, length = 255)
    private String contrasena; // mejor usar String plano y cifrar en servicio

    @Column(name = "FechaCreacion")
    private LocalDateTime fechaCreacion;
}
