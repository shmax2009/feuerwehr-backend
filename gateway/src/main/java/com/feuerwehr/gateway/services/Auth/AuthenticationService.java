package com.feuerwehr.gateway.services.Auth;

import com.feuerwehr.gateway.auth.AuthenticationRequest;
import com.feuerwehr.gateway.auth.AuthenticationResponse;
import com.feuerwehr.gateway.domain.extra.EmptyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public Mono<ResponseEntity<AuthenticationResponse>> authenticate(AuthenticationRequest request) {
        var userMono = userDetailsService.findByUsername(request.getUsername()).defaultIfEmpty(new EmptyUserDetails());
        return userMono.map(user -> {
            if (user.getUsername() == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
            if (encoder.matches(request.getPassword(), user.getPassword())) {
                return ResponseEntity.ok(new AuthenticationResponse(jwtService.generateAuthToken(user),
                        jwtService.generateRefreshToken(user)));
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        });
    }


    public ResponseEntity<AuthenticationResponse> refresh(String auth) {
        String token = extractToken(auth);
        if (token == null || jwtService.isTokenExpired(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        String username = jwtService.extractUsername(token);

        var user = userDetailsService.findByUsername(username).block();

        if (user == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        return ResponseEntity.ok().body(new AuthenticationResponse(jwtService.generateAuthToken(user), token));
    }

    private String extractToken(String auth) {
        if (auth == null || !auth.startsWith("Bearer ")) {
            return null;
        }
        return auth.substring("Bearer ".length());
    }
}