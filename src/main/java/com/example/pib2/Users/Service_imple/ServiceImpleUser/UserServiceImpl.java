package com.example.pib2.Users.Service_imple.ServiceImpleUser;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.pib2.Access.Roles;
import com.example.pib2.Access.AccessRepository.AccessRepository;
import com.example.pib2.Users.model.Entity.TypeClient.TipoClientes;
import com.example.pib2.Users.model.Entity.TypeDocument.TipoDocumento;
import com.example.pib2.Users.model.Entity.User.Users;
import com.example.pib2.Users.model.dto.InsertUser.ClientsInsertDTO;
import com.example.pib2.Users.model.dto.UpdateUser.ClientUpdateDTO;
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
    private final AccessRepository accessRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
            TypeClienteRepository typeClienteRepository,
            TypeDocumentRepository typeDocumentRepository,
            AccessRepository accessRepository) {
        this.userRepository = userRepository;
        this.typeClienteRepository = typeClienteRepository;
        this.typeDocumentRepository = typeDocumentRepository;
        this.accessRepository = accessRepository;
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
                .Username(cliente.getUsername())
                .Email(cliente.getEmail())
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
    public Users createNewClient(ClientsInsertDTO clienteInsert) {

        Optional<TipoClientes> tipoClienteOptional = typeClienteRepository.findById(clienteInsert.getIdTipoCliente());
        Optional<TipoDocumento> tipoDocumentoOptional = typeDocumentRepository.findById(clienteInsert.getIdTipoDocumento());
        Optional<Roles> tiporol = accessRepository.findById(clienteInsert.getIdRol());

        // Si alguna de las entidades no se encuentra, retornamos null.
        if (tipoClienteOptional.isEmpty() || tipoDocumentoOptional.isEmpty()) {
            return null;
        }

        // Obtener los objetos de los Optional si están presentes.
        TipoClientes tipoCliente = tipoClienteOptional.get();
        TipoDocumento tipoDocumento = tipoDocumentoOptional.get();        
        Roles  rol = tiporol.get();

        Users cliente = new Users();
        cliente.setNombreCompleto(clienteInsert.getNombreCompleto());
        cliente.setTelefono(clienteInsert.getTelefono());
        cliente.setNumeroDocumento(clienteInsert.getNumeroDocumento());
        cliente.setFechaNacimiento(clienteInsert.getFechaNacimiento());
        cliente.setActivo(clienteInsert.getActivo());
        cliente.setUsername(clienteInsert.getUsername());
        cliente.setTipoCliente(tipoCliente);
        cliente.setTipoDocumento(tipoDocumento);
        cliente.setEmail(clienteInsert.getEmail());
        cliente.setPassword(passwordEncoder.encode(clienteInsert.getContrasena()));
        cliente.setRol(rol);


        return userRepository.save(cliente);
        
    }

    //Metodo para Actualizar la información de un cliente
    @Override
    public Users updateClient(Long idCliente, ClientUpdateDTO clientUpdate) {
        Users cliente = userRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + idCliente));
        cliente.setNombreCompleto(clientUpdate.getNombreCompleto());
        cliente.setNumeroDocumento(clientUpdate.getNumeroDocumento());
        cliente.setTelefono(clientUpdate.getTelefono());
        cliente.setPassword(clientUpdate.getContrasena());
        

        if (clientUpdate.getNombreCompleto() != null) {
            cliente.setNombreCompleto(clientUpdate.getNombreCompleto());
        }

        if (clientUpdate.getNumeroDocumento() != null) {
            cliente.setNumeroDocumento(clientUpdate.getNumeroDocumento());
        }       

        if (clientUpdate.getTelefono() != null) {
            cliente.setTelefono(clientUpdate.getTelefono());
        }
        if(clientUpdate.getContrasena()!= null){
            cliente.setPassword(clientUpdate.getContrasena());
        }
        return userRepository.save(cliente);
    }

    //Método para actualizar el estado de los clientes (Eliminado Lógico)
    @Override
    public Boolean UpdateStatusCliente(Long idCliente, Boolean activo) {
        try {
            int filas = userRepository.UpdateStatusCliente(idCliente, activo);
            return filas > 0;
        } catch (Exception e) {
            System.err.println("Error al actualizar estado del cliente: " + e.getMessage());
            return false;
        }
    }

    //Metodo para buscar a un cliente por documento
    @Override
    public List<ClientsDTO> getClientByNumeroDocumento(String numeroDocumento) {
        return userRepository.getClientByNumeroDocumento(numeroDocumento)
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
    //Método para buscar cliente por id
    @Override
    public List<ClientsDTO> findByIdCliente(Long idCliente) {
        return userRepository.findByIdCliente(idCliente)
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

    
}
