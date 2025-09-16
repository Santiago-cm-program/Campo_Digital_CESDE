package com.example.pib2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authz -> authz
                //End point públicos para desarrollos y monitores
                .requestMatchers("/actuador/**").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                // Endpoints de Swagger/OpenAPI (públicos)
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/swagger-ui.html").permitAll()
                .requestMatchers("/v3/api-docs/**").permitAll()
                .requestMatchers("/swagger-resources/**").permitAll()
                .requestMatchers("/webjars/**").permitAll()
                //End Point para rol de admin
                .requestMatchers("/v1/api/typeclient/**").hasRole("ADMIN")
                .requestMatchers("/v1/api/TypeDocumento/**").hasRole("ADMIN")
                .requestMatchers("/v1/api/Users/PUT/{id}/**").hasRole("ADMIN")
                .requestMatchers("/v1/api/Users/PATCH/{id}/activo/**").hasRole("ADMIN")
                .requestMatchers("/v1/api/Users/POST/**").hasRole("ADMIN")
                .requestMatchers("/v1/api/Users/Get/**").hasRole("ADMIN")
                .requestMatchers("/v1/api/Users/GET/IdCliente/{idCliente}/**").hasRole("ADMIN")
                .requestMatchers("/v1/api/Users/GET/Documento/{numeroDocumento}/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                )
                //Configurar autenticación HTTP Basic
                .httpBasic(basic -> basic.realmName("PI Backend API"))
                // Configurar política de sesión como stateless (para APIs REST)
                .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // Configurar headers para H2 Console (desarrollo)
                .headers(headers -> headers
                .frameOptions().sameOrigin()
                );

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }        

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    @SuppressWarnings("deprecation")
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
