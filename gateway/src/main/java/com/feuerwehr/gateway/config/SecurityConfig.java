package com.feuerwehr.gateway.config;


import com.feuerwehr.gateway.domain.API.APIRouter;
import com.feuerwehr.gateway.filters.AuthConverter;
import com.feuerwehr.gateway.filters.AuthManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.web.cors.reactive.CorsWebFilter;

import java.net.URI;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

    private final APIRouter APIRouter;
    private final DiscoveryClient discoveryClient;

    private final CorsWebFilter corsWebFilter;

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http, AuthConverter jwtAuthConverter,
                                                     AuthManager jwtAuthManager) {

        AuthenticationWebFilter jwtFilter = new AuthenticationWebFilter(jwtAuthManager);
        jwtFilter.setServerAuthenticationConverter(jwtAuthConverter);

        http.authorizeExchange(auth -> {

                for (var api : APIRouter.getApis()) {
                    auth.pathMatchers("/api/" + api.getVersion() + "/auth/**").permitAll();
                    for (var path : api.getRoutes()) {
                        auth.pathMatchers("/api" + "/" + api.getVersion() + "/" + path.getUrl() + "/user/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN");
                        auth.pathMatchers("/api" + "/" + api.getVersion() + "/" + path.getUrl() + "/admin/**").hasAnyAuthority("ROLE_ADMIN");
                        auth.pathMatchers("/api" + "/" + api.getVersion() + "/" + path.getUrl() + "/public/**").permitAll();
                    }
                }
                auth.anyExchange().authenticated();
            })
            .addFilterAt(jwtFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            .addFilterBefore(corsWebFilter, SecurityWebFiltersOrder.CORS)
            .httpBasic().disable()
            .formLogin().disable()
            .csrf().disable()
            .cors().disable();

        return http.build();
    }


}
