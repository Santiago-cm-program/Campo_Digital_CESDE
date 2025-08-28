package com.example.pib2.Users.controller.TypeDocumentController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pib2.Users.model.dto.TipoDocument.TypeDocumentsDTO;
import com.example.pib2.Users.service.ServiceTypeDocument.TypeDocumentService;


@RestController
@RequestMapping("v1/api/TypeDocumento")
public class TypeDocumentController {
    
    @Autowired
    private TypeDocumentService typeDocumentService;
    
    @GetMapping()
    public ResponseEntity<List<TypeDocumentsDTO>> getTypeDocument() {
    List<TypeDocumentsDTO> typeDocument = typeDocumentService.getAllTypeDocument();  
    return new ResponseEntity<>(typeDocument, HttpStatus.OK);
    }

}
