package com.example.pib2.Access.AccessRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pib2.Access.Roles;

public interface AccessRepository extends JpaRepository<Roles, Long> {

    
}  


