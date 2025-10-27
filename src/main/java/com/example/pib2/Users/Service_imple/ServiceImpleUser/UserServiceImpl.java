package com.example.pib2.Users.Service_imple.ServiceImpleUser;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.pib2.Access.AccessRepository.AccessRepository;
import com.example.pib2.Access.Roles;
import com.example.pib2.Users.model.Entity.Address.Direcciones;
import com.example.pib2.Users.model.Entity.Cities.Ciudades;
import com.example.pib2.Users.model.Entity.TypeClient.TipoClientes;
import com.example.pib2.Users.model.Entity.TypeDocument.TipoDocumento;
import com.example.pib2.Users.model.Entity.User.Users;
import com.example.pib2.Users.model.dto.Address.AddressDTO;
import com.example.pib2.Users.model.dto.InsertUser.ClientsInsertDTO;
import com.example.pib2.Users.model.dto.LoginUserDTO.UserLoginDTO;
import com.example.pib2.Users.model.dto.UpdateClientStatus.UpdateClientStatusDTO;
import com.example.pib2.Users.model.dto.UpdateUser.ClientUpdateDTO;
import com.example.pib2.Users.model.dto.Users.ClientsDTO;
import com.example.pib2.Users.repository.AddresRepository.AddressRepository;
import com.example.pib2.Users.repository.CitiesRepository.CitiesRepository;
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
    private final CitiesRepository citiesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
            TypeClienteRepository typeClienteRepository,
            TypeDocumentRepository typeDocumentRepository,
            AccessRepository accessRepository,
            AddressRepository addressRepository,
            CitiesRepository citiesRepository) {
        this.userRepository = userRepository;
        this.typeClienteRepository = typeClienteRepository;
        this.typeDocumentRepository = typeDocumentRepository;
        this.accessRepository = accessRepository;
        this.citiesRepository = citiesRepository;

    }
    // Metodo Get para obtener toda la información del usuario

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
                .username(cliente.getUsername())
                .Email(cliente.getEmail())
                .idTipoCliente(
                        cliente.getTipoCliente() != null
                        ? cliente.getTipoCliente()
                                .getIdTipoCliente()
                        : null)
                .tipoClienteDescripcion(
                        cliente.getTipoCliente() != null
                        ? cliente.getTipoCliente()
                                .getTipoCliente()
                        : null)
                .idTipoDocumento(cliente.getTipoDocumento() != null
                        ? cliente.getTipoDocumento().getIdTipoDocumento()
                        : null)
                .tipoDocumentoDescripcion(cliente.getTipoDocumento() != null
                        ? cliente.getTipoDocumento().getTipoDocumento()
                        : null)
                .rol(cliente.getRol() != null ? cliente.getRol() : null)
                .build())
                .collect(Collectors.toList());
    }
    // Metodo para insertar la información de un usuario

    @Override
    public Users createNewClient(ClientsInsertDTO clienteInsert) {

        Optional<TipoClientes> tipoClienteOptional = typeClienteRepository
                .findById(clienteInsert.getIdTipoCliente());
        Optional<TipoDocumento> tipoDocumentoOptional = typeDocumentRepository
                .findById(clienteInsert.getIdTipoDocumento());
        Optional<Roles> tiporol = accessRepository.findById(clienteInsert.getIdRol());

        // Validar existencia de entidades
        if (tipoClienteOptional.isEmpty() || tipoDocumentoOptional.isEmpty() || tiporol.isEmpty()) {
            return null;
        }
        Roles RolDefecto = new Roles();
        RolDefecto.setIdRol(2);
        // Crear cliente
        Users cliente = new Users();
        cliente.setNombreCompleto(clienteInsert.getNombreCompleto());
        cliente.setTelefono(clienteInsert.getTelefono());
        cliente.setNumeroDocumento(clienteInsert.getNumeroDocumento());
        cliente.setFechaNacimiento(clienteInsert.getFechaNacimiento());
        cliente.setActivo(clienteInsert.getActivo());
        cliente.setUsername(clienteInsert.getEmail());
        cliente.setTipoCliente(tipoClienteOptional.get());
        cliente.setTipoDocumento(tipoDocumentoOptional.get());
        cliente.setEmail(clienteInsert.getEmail());
        cliente.setPassword(passwordEncoder.encode(clienteInsert.getPassword()));
        cliente.setFechaCreacion(LocalDate.now());
        cliente.setRol(tiporol.orElse(RolDefecto));

        // Procesar direcciones
        AddressDTO direccionDTO = clienteInsert.getDireccion();
        if (direccionDTO != null) {
            Optional<Ciudades> ciudadOpt = citiesRepository.findById(direccionDTO.getCodigoCiudad());
            if (ciudadOpt.isPresent()) {
                Direcciones direccionCliente = new Direcciones();
                direccionCliente.setCodigoCiudad(ciudadOpt.get());
                direccionCliente.setDescripcion(direccionDTO.getDescripcion());
                direccionCliente.setCliente(cliente);

                // como es OneToMany, agregamos la dirección a la lista
                cliente.getDirecciones().add(direccionCliente);
            }
        }

        return userRepository.save(cliente);
    }

    // Metodo para Actualizar la información de un cliente
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
        if (clientUpdate.getContrasena() != null) {
            cliente.setPassword(passwordEncoder.encode((clientUpdate.getContrasena())));
        }
        return userRepository.save(cliente);
    }

    // Metodo para buscar a un cliente por documento
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
                        ? cliente.getTipoCliente()
                                .getIdTipoCliente()
                        : null)
                .tipoClienteDescripcion(
                        cliente.getTipoCliente() != null
                        ? cliente.getTipoCliente()
                                .getTipoCliente()
                        : null)
                .idTipoDocumento(cliente.getTipoDocumento() != null
                        ? cliente.getTipoDocumento().getIdTipoDocumento()
                        : null)
                .tipoDocumentoDescripcion(cliente.getTipoDocumento() != null
                        ? cliente.getTipoDocumento().getTipoDocumento()
                        : null)
                .build())
                .collect(Collectors.toList());
    }

    // Método para buscar cliente por id
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
                        ? cliente.getTipoCliente()
                                .getIdTipoCliente()
                        : null)
                .tipoClienteDescripcion(
                        cliente.getTipoCliente() != null
                        ? cliente.getTipoCliente()
                                .getTipoCliente()
                        : null)
                .idTipoDocumento(cliente.getTipoDocumento() != null
                        ? cliente.getTipoDocumento().getIdTipoDocumento()
                        : null)
                .tipoDocumentoDescripcion(cliente.getTipoDocumento() != null
                        ? cliente.getTipoDocumento().getTipoDocumento()
                        : null)
                .build())
                .collect(Collectors.toList());
    }

    // Metodo para actualizar el estado del clientes
    @Override
    public boolean UpdateStatusCliente(Long idCliente, UpdateClientStatusDTO clientUpdateStatus) {
        Users cliente = userRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + idCliente));
        cliente.setActivo(clientUpdateStatus.isActivo());
        userRepository.save(cliente);
        return true;
    }

    // Metodo para autenticación
    @Override
    public Optional<ClientsDTO> findByUsername(UserLoginDTO loginUser) {
        return userRepository.findByUsername(loginUser.getUsername())
                .filter(user -> passwordEncoder.matches(
                loginUser.getPassword(),
                user.getPassword()))
                .map(user -> ClientsDTO.builder()
                .idCliente(user.getIdCliente())
                .nombreCompleto(user.getNombreCompleto())
                .activo(user.getActivo())
                .username(user.getUsername())
                .rol(user.getRol())
                .password(user.getPassword())
                .Email(user.getEmail())
                .direccion(user.getDirecciones()
                        .stream()
                        .findFirst()
                        .map(dir -> AddressDTO.builder()
                        .codigoCiudad(dir.getCodigoCiudad().getCodigoCiudad())
                        .descripcion(dir.getDescripcion())
                        .build())
                        .orElse(null))
                .build());
    }

}
