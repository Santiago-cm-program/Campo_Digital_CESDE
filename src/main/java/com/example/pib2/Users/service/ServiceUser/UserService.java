package com.example.pib2.Users.service.ServiceUser;

import java.util.List;
import java.util.Optional;

import com.example.pib2.Users.model.Entity.User.Users;
import com.example.pib2.Users.model.dto.InsertUser.ClientsInsertDTO;
import com.example.pib2.Users.model.dto.LoginUserDTO.UserLoginDTO;
import com.example.pib2.Users.model.dto.UpdateClientStatus.UpdateClientStatusDTO;
import com.example.pib2.Users.model.dto.UpdateUser.ClientUpdateDTO;
import com.example.pib2.Users.model.dto.Users.ClientsDTO;

public interface UserService {

    //Listar todos los Clientes
    List<ClientsDTO> getAllClients();

    //Crear cliente
    Users createNewClient(ClientsInsertDTO clienteInsert);

    //Actualizar Usuario
    Users updateClient(Long idCliente, ClientUpdateDTO clientUpdate);

    //Inactivación del cliente
    boolean UpdateStatusCliente(Long idCliente, UpdateClientStatusDTO clientUpdateStatus);

    // Consultar cliente por numero de documento 
    List<ClientsDTO> getClientByNumeroDocumento(String numeroDocumento);

    //Consultar cliente por id
    List<ClientsDTO> findByIdCliente(Long idCliente);

    Optional<ClientsDTO> findByUsername (UserLoginDTO loginUser);
}
