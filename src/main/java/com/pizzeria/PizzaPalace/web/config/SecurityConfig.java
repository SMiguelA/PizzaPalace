package com.pizzeria.PizzaPalace.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    // Permitimos que spring pueda lanzar exceptiones y lo anotamos con Bean para que lo cree como un bean inyectable a la app
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        // para que permita cualquier tipo de peticion http
        // definimos el tipo de permisos http
        http.authorizeHttpRequests(
                customizedRequests -> {
                    customizedRequests
                            .anyRequest()
                            .authenticated();
                }).httpBasic(Customizer.withDefaults());
                // Indicamos que tipo de autenticador utilizar, en este caso el basic
        return http.build();
    }
}
