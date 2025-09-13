package com.example.pib2.Users.repository.UsersRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.pib2.Users.model.Entity.User.Users;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<Users, Long> {

    //Métodos personalizados
    //Método personalizado para inactivar cliente
    @Modifying
    @Transactional
    @Query("UPDATE Users c SET c.activo = :activo WHERE c.idCliente = :idCliente")
    int UpdateStatusCliente(Long idCliente, Boolean activo);

    //Metodo personalizado para buscar por numero de documento
    List<Users> getClientByNumeroDocumento(String numeroDocumento);

    //Metodo para obtener cliente por id
    List<Users> findByIdCliente(Long idCliente);

    Optional<Users> findByUsername(String username);

}
