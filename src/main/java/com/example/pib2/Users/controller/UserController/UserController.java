package com.example.pib2.Users.controller.UserController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pib2.Users.model.Entity.User.Users;
import com.example.pib2.Users.model.dto.InsertUser.ClientsInsertDTO;
import com.example.pib2.Users.model.dto.LoginUserDTO.UserLoginDTO;
import com.example.pib2.Users.model.dto.UpdateClientStatus.UpdateClientStatusDTO;
import com.example.pib2.Users.model.dto.UpdateUser.ClientUpdateDTO;
import com.example.pib2.Users.model.dto.Users.ClientsDTO;
import com.example.pib2.Users.service.ServiceUser.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@CrossOrigin(origins = "http://localhost:3000") 
@RequestMapping("v1/api/Users")
@SecurityRequirement(name = "basicAuth")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("GET")
    @Operation(summary = "Obtener todos los usuarios", description = "End Point para obtener todos los usuarios de la base de datos")
    public ResponseEntity<List<ClientsDTO>> getTypeDocument() {
        List<ClientsDTO> Clients = userService.getAllClients();
        return new ResponseEntity<>(Clients, HttpStatus.OK);
    }

    @PostMapping("POST")
    @Operation(summary = "Crear un nuevo usuario", description = "End Point para crear un usuario en la base de datos")
    public ResponseEntity<Users> createNewClient(@RequestBody ClientsInsertDTO clienteInsert) {
        try {
            Users NewCliente = userService.createNewClient(clienteInsert);
            return new ResponseEntity<>(NewCliente, HttpStatus.CREATED);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("PUT/{id}")
    @Operation(summary = "Modifica información de un usuario", description = "End Point que te permite modificar información de un usuario, se debe buscar el usuario mediante el id")
    public ResponseEntity<Users> updateCliente(
            @PathVariable Long id,
            @RequestBody ClientUpdateDTO clientUpdate) {

        Users actualizado = userService.updateClient(id, clientUpdate);
        return ResponseEntity.ok(actualizado);
    }

    @PatchMapping("PATCH/{id}")
    @Operation(summary = "Inactivar Usuario", description = "End Point que te permite inactivar usuarios")
    public ResponseEntity<String> UpdateStatusCliente(
            @PathVariable("id") Long idCliente,
            @RequestBody UpdateClientStatusDTO isActive) {

        boolean updateStatus = userService.UpdateStatusCliente(idCliente, isActive);

        if (updateStatus) {
            return ResponseEntity.ok("Estado actualizado correctamente.");
        } else {
            return ResponseEntity.badRequest().body("No se encontró el cliente.");
        }
    }

    @GetMapping("GET/Documento/{numeroDocumento}")
    @Operation(summary = "Obtener usuario por numero de documentos", description = "End Point que te permite obtener usuario por el numero del documento")
    public ResponseEntity<List<ClientsDTO>> getByNumberDocument(@PathVariable String numeroDocumento) {
        List<ClientsDTO> client = userService.getClientByNumeroDocumento(numeroDocumento);
        return ResponseEntity.ok(client);
    }

    @GetMapping("GET/IdCliente/{idCliente}")
    @Operation(summary = "Obtener el usuario por id", description = "End Point para obtener el usuario por el id")
    public ResponseEntity<List<ClientsDTO>> findByIdCliente(@PathVariable Long idCliente) {
        List<ClientsDTO> client = userService.findByIdCliente(idCliente);

        if (!client.isEmpty()) {
            return ResponseEntity.ok(client); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(client);
        }
    }

  
    @PostMapping("POST/Login")
    @Operation(summary = "validar login", description = "End Point para obtener el JSON del usuario")
    public ResponseEntity<ClientsDTO> LogginAccess(@RequestBody UserLoginDTO loginUser) {
         return userService.findByUsername(loginUser)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null));
        
    }
    

}
