package com.example.pib2.Products.repository;

import com.example.pib2.Products.model.Entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Productos, Long>{

    @Query("SELECT p FROM Productos p JOIN FETCH p.categoria")
    List<Productos> getAllWithCategories();

    @Query("SELECT p FROM Productos p JOIN FETCH p.categoria WHERE p.idProducto = :productoId")
    Optional<Productos> getByIdWithCategories(@Param("productoId") Long productoId);
}
