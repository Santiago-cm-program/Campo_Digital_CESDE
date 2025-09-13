package com.example.pib2.Access;

import java.sql.Date;
import java.util.List;

import com.example.pib2.Users.model.Entity.User.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRol;

    @Column(name = "Descripcion", length = 100, nullable = false)
    private String descripcion;

    @Column(name = "FechaCreacion")
    private Date fechaCreacion;

    @Column(name = "Activo")
    private Boolean activo;   

    @JsonBackReference// importante para evitar el bucle
    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Users> clientes;

}
