package com.example.pib2.Users.repository.AddresRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pib2.Users.model.Entity.Address.Direcciones;

public interface  AddressRepository extends  JpaRepository<Direcciones, Long> {

}
