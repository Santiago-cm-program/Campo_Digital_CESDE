package com.example.pib2.Users.repository.UsersRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pib2.Users.model.Entity.User.Clientes;

public interface  UserRepository extends JpaRepository<Clientes, Long> {

}
