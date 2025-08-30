package com.example.pib2.Users.controller.UserController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pib2.Users.model.Entity.User.Clientes;
import com.example.pib2.Users.model.dto.InsertUser.ClientsInsertDTO;
import com.example.pib2.Users.model.dto.UpdateUser.ClientUpdateDTO;
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

    @PostMapping("POST")
    public ResponseEntity<Clientes> createNewClient(@RequestBody ClientsInsertDTO clienteInsert) {
        try {
            Clientes NewCliente = userService.createNewClient(clienteInsert);
            return new ResponseEntity<>(NewCliente, HttpStatus.CREATED);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("PUT/{id}")
    public ResponseEntity<Clientes> updateCliente(
            @PathVariable Long id,
            @RequestBody ClientUpdateDTO clientUpdate) {

        Clientes actualizado = userService.updateClient(id, clientUpdate);
        return ResponseEntity.ok(actualizado);
    }

    @PutMapping("PUT/{id}/activo")
    public ResponseEntity<String> UpdateStatusCliente(@PathVariable("id") Long idCliente,
            @RequestParam("activo") boolean activo) {

        boolean updateStatus = userService.UpdateStatusCliente(idCliente, activo);
        if (updateStatus) {
            return ResponseEntity.ok("Estado actualizado correctamente.");
        } else {
            return ResponseEntity.badRequest().body("No se encontró el cliente.");
        }
    }

    @GetMapping("GET/Documento/{numeroDocumento}")
    public ResponseEntity<List<ClientsDTO>> getByNumberDocument(@PathVariable String numeroDocumento) {
        List<ClientsDTO> client = userService.getClientByNumeroDocumento(numeroDocumento);
        return ResponseEntity.ok(client);
    }

    @GetMapping("GET/IdCliente/{idCliente}")
    public ResponseEntity<List<ClientsDTO>> findByIdCliente(@PathVariable Long idCliente) {
        List<ClientsDTO> client = userService.findByIdCliente(idCliente);

        if (!client.isEmpty()) {
            return ResponseEntity.ok(client); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(client); 
        }
    }

}
