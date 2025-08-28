package com.example.pib2.Users.Service_imple.ServiceImpleUser;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pib2.Users.model.Entity.TypeClient.TipoClientes;
import com.example.pib2.Users.model.Entity.TypeDocument.TipoDocumento;
import com.example.pib2.Users.model.Entity.User.Clientes;
import com.example.pib2.Users.model.dto.InsertUser.ClientsInsertDTO;
import com.example.pib2.Users.model.dto.Users.ClientsDTO;
import com.example.pib2.Users.repository.TypeClienteRepository.TypeClienteRepository;
import com.example.pib2.Users.repository.TypeDocumentRepository.TypeDocumentRepository;
import com.example.pib2.Users.repository.UsersRepository.UserRepository;
import com.example.pib2.Users.service.ServiceUser.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;
    private final TypeClienteRepository typeClienteRepository;
    private final TypeDocumentRepository typeDocumentRepository;

    public UserServiceImpl(UserRepository userRepository,
                           TypeClienteRepository typeClienteRepository,
                           TypeDocumentRepository typeDocumentRepository) {
        this.userRepository = userRepository;
        this.typeClienteRepository = typeClienteRepository;
        this.typeDocumentRepository = typeDocumentRepository;
    }
//Metodo Get para obtener toda la información del usuario
    @Override
    public List<ClientsDTO> getAllClients() {
        return userRepository.findAll()
                .stream()
                .map(cliente -> ClientsDTO.builder()
                .idCliente(cliente.getIdCliente())
                .nombreCompleto(cliente.getNombreCompleto())
                .telefono(cliente.getTelefono())
                .numeroDocumento(cliente.getNumeroDocumento())
                .activo(cliente.getActivo())
                .fechaNacimiento(cliente.getFechaNacimiento())
                .idTipoCliente(
                        cliente.getTipoCliente() != null
                        ? cliente.getTipoCliente().getIdTipoCliente() : null)
                .tipoClienteDescripcion(
                        cliente.getTipoCliente() != null
                        ? cliente.getTipoCliente().getTipoCliente() : null)
                .idTipoDocumento(cliente.getTipoDocumento() != null
                        ? cliente.getTipoDocumento().getIdTipoDocumento() : null)
                .tipoDocumentoDescripcion(cliente.getTipoDocumento() != null
                        ? cliente.getTipoDocumento().getTipoDocumento() : null)
                .build()).collect(Collectors.toList());
    }
//Metodo para insertar la información de un usuario
    @Override
    public Clientes createNewClient(ClientsInsertDTO clienteInsert) {

        Optional<TipoClientes> tipoClienteOptional = typeClienteRepository.findById(clienteInsert.getIdTipoCliente());
        Optional<TipoDocumento> tipoDocumentoOptional = typeDocumentRepository.findById(clienteInsert.getIdTipoDocumento());
        
        // Si alguna de las entidades no se encuentra, retornamos null.
        if (tipoClienteOptional.isEmpty() || tipoDocumentoOptional.isEmpty()) {
            return null;
        }
        
        // Obtener los objetos de los Optional si están presentes.
        TipoClientes tipoCliente = tipoClienteOptional.get();
        TipoDocumento tipoDocumento = tipoDocumentoOptional.get();

        Clientes cliente = new Clientes();
        cliente.setNombreCompleto(clienteInsert.getNombreCompleto());
        cliente.setTelefono(clienteInsert.getTelefono());
        cliente.setNumeroDocumento(clienteInsert.getNumeroDocumento());
        cliente.setFechaNacimiento(clienteInsert.getFechaNacimiento());
        cliente.setActivo(clienteInsert.getActivo());
        cliente.setTipoCliente(tipoCliente);
        cliente.setTipoDocumento(tipoDocumento);
        return userRepository.save(cliente);
    }
}
