package com.example.pib2.Users.service.ServiceUser;

import java.util.List;

import com.example.pib2.Users.model.Entity.User.Clientes;
import com.example.pib2.Users.model.dto.InsertUser.ClientsInsertDTO;
import com.example.pib2.Users.model.dto.UpdateUser.ClientUpdateDTO;
import com.example.pib2.Users.model.dto.Users.ClientsDTO;

public interface UserService {

    //Listar todos los Clientes
    List<ClientsDTO> getAllClients();

    //Crear cliente
    Clientes createNewClient(ClientsInsertDTO clienteInsert);

    //Actualizar Usuario
    Clientes updateClient(Long idCliente, ClientUpdateDTO clientUpdate);

    //Inactivación del cliente
    Boolean UpdateStatusCliente(Long idCliente, Boolean activo);

    // Consultar cliente por numero de documento 
    List<ClientsDTO> getClientByNumeroDocumento(String numeroDocumento);

    //Consultar cliente por id
    List<ClientsDTO> findByIdCliente(Long idCliente);
}
