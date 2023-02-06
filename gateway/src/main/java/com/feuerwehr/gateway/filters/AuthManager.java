package com.feuerwehr.gateway.filters;

import com.feuerwehr.gateway.domain.extra.BearerToken;
import com.feuerwehr.gateway.domain.extra.EmptyUserDetails;
import com.feuerwehr.gateway.domain.extra.NoAuthentication;
import com.feuerwehr.gateway.services.Auth.JwtService;
import com.feuerwehr.gateway.services.Auth.UserDetailsService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


@Data
@AllArgsConstructor
@Component
public class AuthManager implements ReactiveAuthenticationManager {

    private final JwtService jwtService;

    final UserDetailsService userDetailsService;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.justOrEmpty(authentication)
                .cast(BearerToken.class)
                .flatMap(auth -> {
                    if (jwtService.isTokenExpired(auth.getCredentials()))
                        return Mono.justOrEmpty(new NoAuthentication());
                    var username = jwtService.extractUsername(auth.getCredentials());
                    var foundUser = userDetailsService.findByUsername(username).defaultIfEmpty(new EmptyUserDetails());

                    return foundUser.flatMap(u -> {
                        if (u.getUsername() == null) {
                            return Mono.justOrEmpty(new NoAuthentication());
                        }

                        if (jwtService.isTokenValid(auth.getCredentials(), u)) {
                            return Mono.justOrEmpty(new UsernamePasswordAuthenticationToken(u.getUsername(), u.getPassword(),
                                    u.getAuthorities()));
                        }
                        return Mono.justOrEmpty(new NoAuthentication());
                    });
                });
    }
}
