package com.example.pib2.Users.service.ServiceUser;

import java.util.List;

import com.example.pib2.Users.model.Entity.User.Clientes;
import com.example.pib2.Users.model.dto.InsertUser.ClientsInsertDTO;
import com.example.pib2.Users.model.dto.Users.ClientsDTO;

public interface  UserService {    
    List<ClientsDTO> getAllClients();

    Clientes createNewClient(ClientsInsertDTO clienteInsert);
    
}
