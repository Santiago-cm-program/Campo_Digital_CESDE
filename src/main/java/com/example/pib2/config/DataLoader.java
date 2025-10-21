package com.example.pib2.config;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.pib2.Access.AccessRepository.AccessRepository;
import com.example.pib2.Access.Roles;
import com.example.pib2.Users.model.Entity.TypeClient.TipoClientes;
import com.example.pib2.Users.model.Entity.TypeDocument.TipoDocumento;
import com.example.pib2.Users.model.Entity.User.Users;
import com.example.pib2.Users.repository.TypeClienteRepository.TypeClienteRepository;
import com.example.pib2.Users.repository.TypeDocumentRepository.TypeDocumentRepository;
import com.example.pib2.Users.repository.UsersRepository.UserRepository;

@Configuration
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccessRepository accessRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TypeClienteRepository tipoClienteRepository;

    @Autowired
    private TypeDocumentRepository tipoDocumentoRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("SCORREA").isEmpty()) {

            TipoClientes tipoCliente = tipoClienteRepository.findById(1L).orElse(null);
            TipoDocumento tipoDocumento = tipoDocumentoRepository.findById(1L).orElse(null);
            Roles rol = new Roles();
            rol.setDescripcion("ADMIN");
            rol.setActivo(true);
            rol.setFechaCreacion(new java.sql.Date(System.currentTimeMillis()));
            accessRepository.save(rol);

            Users admin = new Users();

            admin.setUsername("SCORREA");
            admin.setEmail("stillsantiago@gmail.com");
            admin.setNombreCompleto("Santiago Correa Mejia");
            admin.setNumeroDocumento("1020485975");
            admin.setActivo(true);
            admin.setPassword(passwordEncoder.encode("980522Sc"));
            admin.setFechaNacimiento(Date.valueOf("1995-09-05"));
            admin.setEnabled(true);
            admin.setAccountNonExpired(true);
            admin.setAccountNonLocked(true);
            admin.setCredentialsNonExpired(true);
            admin.setRol(rol);
            admin.setTipoCliente(tipoCliente);
            admin.setTipoDocumento(tipoDocumento);
            admin.setTelefono("3023182092");

            userRepository.save(admin);

            System.out.println("Usuario ADMIN Creado: Usarname: SCORREA, Password: 980522Sc");
        }

        if (userRepository.findByUsername("JVALLEJO").isEmpty()) {

            TipoClientes tipoCliente = tipoClienteRepository.findById(1L).orElse(null);
            TipoDocumento tipoDocumento = tipoDocumentoRepository.findById(1L).orElse(null);
            Roles rol = new Roles();
            rol.setDescripcion("ADMIN");
            rol.setActivo(true);
            rol.setFechaCreacion(new java.sql.Date(System.currentTimeMillis()));
            accessRepository.save(rol);

            Users admin = new Users();

            admin.setUsername("JVALLEJO");
            admin.setEmail("Julian.vallejo@gmail.com");
            admin.setNombreCompleto("Julian Vallejo");
            admin.setNumeroDocumento("1030456214");
            admin.setActivo(true);
            admin.setPassword(passwordEncoder.encode("Colombia123*"));
            admin.setFechaNacimiento(Date.valueOf("1995-09-05"));
            admin.setEnabled(true);
            admin.setAccountNonExpired(true);
            admin.setAccountNonLocked(true);
            admin.setCredentialsNonExpired(true);
            admin.setRol(rol);
            admin.setTipoCliente(tipoCliente);
            admin.setTipoDocumento(tipoDocumento);
            admin.setTelefono("3176388030");

            userRepository.save(admin);

            System.out.println("Usuario ADMIN Creado: Usarname: JBEDOYA, Password: Colombia123*");
        }

        if (userRepository.findByUsername("CBEDOYA").isEmpty()) {

            TipoClientes tipoCliente = tipoClienteRepository.findById(1L).orElse(null);
            TipoDocumento tipoDocumento = tipoDocumentoRepository.findById(1L).orElse(null);
            Roles rol = new Roles();
            rol.setDescripcion("ADMIN");
            rol.setActivo(true);
            rol.setFechaCreacion(new java.sql.Date(System.currentTimeMillis()));
            accessRepository.save(rol);

            Users admin = new Users();

            admin.setUsername("CBEDOYA");
            admin.setEmail("cristian.bedoya@gmail.com");
            admin.setNombreCompleto("Cristian Bedoya");
            admin.setNumeroDocumento("1030456214");
            admin.setActivo(true);
            admin.setPassword(passwordEncoder.encode("Colombia123*"));
            admin.setFechaNacimiento(Date.valueOf("1995-09-05"));
            admin.setEnabled(true);
            admin.setAccountNonExpired(true);
            admin.setAccountNonLocked(true);
            admin.setCredentialsNonExpired(true);
            admin.setRol(rol);
            admin.setTipoCliente(tipoCliente);
            admin.setTipoDocumento(tipoDocumento);
            admin.setTelefono("3176388030");

            userRepository.save(admin);

            System.out.println("Usuario ADMIN Creado: Usarname: CBEDOYA, Password: Colombia123*");
        }
    }

}
