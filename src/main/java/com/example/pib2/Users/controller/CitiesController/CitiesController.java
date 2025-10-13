package com.example.pib2.Users.controller.CitiesController;


import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pib2.Users.service.ServiceCities.CitiesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.web.bind.annotation.GetMapping;

import com.example.pib2.Users.model.dto.Departaments.DepartamentsDTO;



@RestController
@CrossOrigin(origins = "http://localhost:3000") 
@RequestMapping("v1/api/Users")
@SecurityRequirement(name = "basicAuth")
public class CitiesController {

    private final CitiesService citiesService;

    
    public CitiesController(CitiesService citiesService) {
        this.citiesService = citiesService;
    }

    @GetMapping("/departamentos")
    @Operation(summary="Obtener los departamentos", description="End Point para consultar los departamentos y las ciudades")
    public ResponseEntity<List<DepartamentsDTO>> getAllDepartament(){
        List<DepartamentsDTO> depart = citiesService.getAllDepartaments();
        return ResponseEntity.ok(depart);
    }
}
    
    
