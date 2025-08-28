package com.example.pib2.Users.Service_imple.serviceImpletTypeClient;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.pib2.Users.model.dto.TypeClientsDTO.TypeClientsDTO;
import com.example.pib2.Users.repository.TypeClienteRepository.TypeClienteRepository;
import com.example.pib2.Users.service.serviceTypeClient.TypeClienteService;

@Service
public class TypeClientServiceImpl implements TypeClienteService {

    private final TypeClienteRepository typeClienteRepository;

    public TypeClientServiceImpl(TypeClienteRepository typeClienteRepository) {
        this.typeClienteRepository = typeClienteRepository;
    }

    @Override
    public List<TypeClientsDTO> getAllTypeClients() {
        return typeClienteRepository.findAll()
                .stream()
                .map(entity -> new TypeClientsDTO(
                entity.getIdTipoCliente(),
                entity.getTipoCliente() // ← aquí usamos el nombre correcto de la propiedad en la entidad
        ))
                .toList();
    }
}
