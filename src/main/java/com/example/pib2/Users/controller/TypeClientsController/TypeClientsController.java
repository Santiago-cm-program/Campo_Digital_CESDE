package com.example.pib2.Users.controller.TypeClientsController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pib2.Users.model.dto.TypeClientsDTO.TypeClientsDTO;
import com.example.pib2.Users.service.serviceTypeClient.TypeClienteService;

@RestController
@RequestMapping("v1/api/typeclient")
public class TypeClientsController {

    @Autowired
    private TypeClienteService typeClienteService;

    @GetMapping()
    public ResponseEntity<List<TypeClientsDTO>> getTypeClient() {
        List<TypeClientsDTO> typeClient = typeClienteService.getAllTypeClients();
        return new ResponseEntity<>(typeClient, HttpStatus.OK);
    }
}
