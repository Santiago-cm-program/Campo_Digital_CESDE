package com.example.pib2.Users.model.Entity.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.pib2.Access.Roles;

import com.example.pib2.Users.model.Entity.TypeClient.TipoClientes;
import com.example.pib2.Users.model.Entity.TypeDocument.TipoDocumento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Users")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users implements UserDetails {

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

    @Column(name = "Username", nullable = false)
    private String username;

    @Column(name = "Password", nullable = false)
    private String Password;

    @Column(name = "Email", nullable = false, length = 255)
    private String email;

    @Column(name = "FechaCreacion")
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JsonManagedReference // importante para evita el bucle
    @JoinColumn(name = "IdRol", unique = true, nullable = false)
    private Roles rol;

    @Column(nullable = false)
    private boolean enabled = true;

    @Column(nullable = false)
    private boolean accountNonExpired = true;

    @Column(nullable = false)
    private boolean accountNonLocked = true;

    @Column(nullable = false)
    private boolean credentialsNonExpired = true;

    // Implementación de UserDetails
    /**
     * Retorna las autoridades (roles) del usuario. Convierte el rol string en
     * una GrantedAuthority con prefijo ROLE_.
     */
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + rol));
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return enabled;
    }
}
