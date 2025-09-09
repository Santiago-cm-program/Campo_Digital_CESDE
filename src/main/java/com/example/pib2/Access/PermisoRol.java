package com.example.pib2.Access;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class PermisoRol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPermisoRol;

    @ManyToOne
    @JoinColumn(name = "IdRol", nullable = false)
    @JsonBackReference // evita recursión infinita
    private Roles rol;

    @ManyToOne
    @JoinColumn(name = "IdPermiso", nullable = false)
    @JsonBackReference // evita recursión infinita
    private Permissions permiso;

    @Column(name = "FechaCreacion", nullable = false)
    private Date fechaCreacion;

}
