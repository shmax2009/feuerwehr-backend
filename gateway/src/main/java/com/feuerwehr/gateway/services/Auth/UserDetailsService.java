package com.feuerwehr.gateway.services.Auth;

import com.feuerwehr.gateway.domain.entity.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@AllArgsConstructor
public class UserDetailsService {


    private final UserRepository repository;

    public Mono<UserDetails> findByUsername(String username) {
        return Mono.justOrEmpty(repository.findByUsername(username));
    }
}
