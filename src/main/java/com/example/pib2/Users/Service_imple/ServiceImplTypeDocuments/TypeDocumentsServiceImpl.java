package com.example.pib2.Users.Service_imple.ServiceImplTypeDocuments;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pib2.Users.model.dto.TipoDocument.TypeDocumentsDTO;
import com.example.pib2.Users.repository.TypeDocumentRepository.TypeDocumentRepository;
import com.example.pib2.Users.service.ServiceTypeDocument.TypeDocumentService;

@Service
public class TypeDocumentsServiceImpl implements TypeDocumentService {

    @Autowired
    private final TypeDocumentRepository typeDocumentRepository;

    public TypeDocumentsServiceImpl(TypeDocumentRepository typeDocumentRepository) {
        this.typeDocumentRepository = typeDocumentRepository;
    }

    @Override
    public List<TypeDocumentsDTO> getAllTypeDocument() {
        return typeDocumentRepository.findAll()
                .stream()
                .map(entity -> new TypeDocumentsDTO(
                    entity.getIdTipoDocumento(),
                    entity.getTipoDocumento()
                )).toList();
    }

}
