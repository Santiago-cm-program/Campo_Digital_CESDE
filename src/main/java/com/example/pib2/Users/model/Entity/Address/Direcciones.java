package com.example.pib2.Users.model.Entity.Address;

import com.example.pib2.Users.model.Entity.Cities.Ciudades;
import com.example.pib2.Users.model.Entity.User.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class Direcciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdDireccion")
    private Long idDireccion;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "CodigoCiudad", referencedColumnName = "codigoCiudad", nullable = false)
    private Ciudades codigoCiudad;

    @ManyToOne
    @JoinColumn(name = "IdCliente", referencedColumnName = "idCliente", nullable = false)
    @JsonBackReference
    private Users cliente;

    @Column(name = "Descripcion", length = 255)
    private String descripcion;
}
