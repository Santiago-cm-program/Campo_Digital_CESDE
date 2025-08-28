package com.example.pib2.Users.model.Entity.Cities;

import com.example.pib2.Users.model.Entity.Departments.Departamentos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ciudades {

    @Id
    @Column(name = "CodigoCiudad")
    private String codigoCiudad;

    @ManyToOne
    @JoinColumn(name = "CodigoDepartamento", referencedColumnName = "codigoDepartamento", nullable = false)
    private Departamentos departamento;

    @Column(name = "Ciudad", length = 255, nullable = false)
    private String ciudad;
}
