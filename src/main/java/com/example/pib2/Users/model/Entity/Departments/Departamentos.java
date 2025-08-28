package com.example.pib2.Users.model.Entity.Departments;

import java.util.List;

import com.example.pib2.Users.model.Entity.Cities.Ciudades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Departamentos {

    @Id
    @Column(name = "CodigoDepartamento")
    private String codigoDepartamento; 

    @Column(name = "Departamento", length = 255, nullable = false)
    private String departamento;

    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ciudades> ciudades;
}
