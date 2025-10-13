package com.example.pib2.Users.Service_imple.ServiceImpletCities;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.pib2.Users.model.dto.Cities.CitiesDTO;
import com.example.pib2.Users.model.dto.Departaments.DepartamentsDTO;
import com.example.pib2.Users.repository.DepartamentRepository.DepartamentRepository;
import com.example.pib2.Users.service.ServiceCities.CitiesService;

@Service
public class CitiesServicesImplemt implements CitiesService {

    private final DepartamentRepository departamentRepository;

    // ✅ Inyección por constructor (Spring lo detecta automáticamente)
    public CitiesServicesImplemt(DepartamentRepository departamentRepository) {
        this.departamentRepository = departamentRepository;
    }

    @Override
    public List<DepartamentsDTO> getAllDepartaments() {
        return departamentRepository.findAll()
                .stream()
                .map(departament -> DepartamentsDTO.builder()
                        .codigoDepartamento(departament.getCodigoDepartamento())
                        .departamento(departament.getDepartamento())
                        .ciudades(
                                departament.getCiudades() != null
                                        ? departament.getCiudades().stream()
                                                .map(city -> CitiesDTO.builder()
                                                        .codigoCiudad(city.getCodigoCiudad())
                                                        .ciudad(city.getCiudad())
                                                        .build())
                                                .collect(Collectors.toList())
                                        : Collections.emptyList()
                        )
                        .build())
                .collect(Collectors.toList());
    }

   
}
