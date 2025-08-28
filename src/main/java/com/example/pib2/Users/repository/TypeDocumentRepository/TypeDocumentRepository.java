package com.example.pib2.Users.repository.TypeDocumentRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pib2.Users.model.Entity.TypeDocument.TipoDocumento;

public interface  TypeDocumentRepository extends JpaRepository<TipoDocumento, Long> {

}
