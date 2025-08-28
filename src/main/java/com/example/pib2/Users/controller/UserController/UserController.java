package com.example.pib2.Users.controller.UserController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pib2.Users.model.Entity.User.Clientes;
import com.example.pib2.Users.model.dto.InsertUser.ClientsInsertDTO;
import com.example.pib2.Users.model.dto.Users.ClientsDTO;
import com.example.pib2.Users.service.ServiceUser.UserService;

@RestController
@RequestMapping("v1/api/Users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<ClientsDTO>> getTypeDocument() {
        List<ClientsDTO> Clients = userService.getAllClients();
        return new ResponseEntity<>(Clients, HttpStatus.OK);
    }

    @PostMapping("Post")
    public ResponseEntity<Clientes> createNewClient(@RequestBody ClientsInsertDTO clienteInsert) {
        try {
            Clientes NewCliente = userService.createNewClient(clienteInsert);
            return new ResponseEntity<>(NewCliente, HttpStatus.CREATED);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
