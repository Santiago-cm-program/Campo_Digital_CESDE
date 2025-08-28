package com.example.pib2.Users.model.Entity.TypeDocument;

import java.util.ArrayList;
import java.util.List;

import com.example.pib2.Users.model.Entity.User.Clientes;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdTipoDocumento;

    @Column(name= "TipoDocumento", length=100, nullable=false)
    private String TipoDocumento;

    @OneToMany(mappedBy = "tipoDocumento", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Clientes> clientes = new ArrayList<>();
}
