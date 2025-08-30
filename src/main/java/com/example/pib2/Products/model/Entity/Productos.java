package com.example.pib2.Products.model.Entity;


import com.example.pib2.Categories.model.Entity.Categorias;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productos")
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdProducto")
    private Long idProducto;

    @Column(name = "Producto", nullable = false, length = 50)
    private String producto;

    @Column(name = "Descripcion", nullable = false, length = 100)
    private String descripcion;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(name = "IdUnidad", nullable = false)
    private Integer idUnidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdCategoria")
    private Categorias categoria;
    
}

