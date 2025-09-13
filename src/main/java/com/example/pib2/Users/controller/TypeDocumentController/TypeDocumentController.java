package com.example.pib2.Users.controller.TypeDocumentController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pib2.Users.model.dto.TipoDocument.TypeDocumentsDTO;
import com.example.pib2.Users.service.ServiceTypeDocument.TypeDocumentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@RestController
@RequestMapping("v1/api/TypeDocumento")
@SecurityRequirement(name="basicAuth")
@CrossOrigin(origins = "*")
public class TypeDocumentController {
    
    @Autowired
    private TypeDocumentService typeDocumentService;
    
    @GetMapping()
    @Operation(summary="Obtener todos los tipos de documentos", description="End Point para obtener todos los tipos de documentos que acepta la aplicación")
    public ResponseEntity<List<TypeDocumentsDTO>> getTypeDocument() {
    List<TypeDocumentsDTO> typeDocument = typeDocumentService.getAllTypeDocument();  
    return new ResponseEntity<>(typeDocument, HttpStatus.OK);
    }

}
