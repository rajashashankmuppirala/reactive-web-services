package com.shashank.reactive.accountservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private final String ROLE_READ_ONLY = "READ_ONLY";
    private final String ROLE_SUPER_USER = "SUPER_USER";

    @Autowired
    KeycloakAuthenticationConverter keycloakAuthenticationConverter;

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange()
                .pathMatchers(HttpMethod.GET,"/**/accounts/**").hasAnyRole(ROLE_READ_ONLY,ROLE_SUPER_USER)
                .pathMatchers(HttpMethod.GET,"/**/account/**").hasAnyRole(ROLE_READ_ONLY,ROLE_SUPER_USER)
                .pathMatchers(HttpMethod.POST,"/**/account/**").hasAnyRole(ROLE_SUPER_USER)
                .pathMatchers(HttpMethod.PUT,"/**/account/**").hasAnyRole(ROLE_SUPER_USER)
                .pathMatchers(HttpMethod.DELETE,"/**/account/**").hasAnyRole(ROLE_SUPER_USER)
                .pathMatchers("/instances").permitAll()
                .pathMatchers("/actuator/**").permitAll()
                .anyExchange().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(jwtAuthenticationConverter()));
        return http.build();
    }

    @Bean
    ReactiveJwtAuthenticationConverter jwtAuthenticationConverter() {
        ReactiveJwtAuthenticationConverter reactiveJwtAuthenticationConverter = new ReactiveJwtAuthenticationConverter();
        reactiveJwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(keycloakAuthenticationConverter);
        return reactiveJwtAuthenticationConverter;
    }

}
