package com.feuerwehr.gateway.services.Auth;

import com.feuerwehr.gateway.domain.entity.Token;
import com.feuerwehr.gateway.domain.entity.TokenRepository;
import com.feuerwehr.gateway.domain.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class TokenService {
    private final BCryptPasswordEncoder encoder;
    private final TokenRepository repository;

    public boolean invalidToken(String _token) {
        Optional<Token> token = repository.findAll().stream().filter(t -> encoder.matches(_token, t.getToken())).findFirst();
        return token.isEmpty();
    }

    public void updateTokenRepository(String username, String nextToken, Type type) {

        var currentToken = repository.getByUsernameAndType(username, type);
        if (currentToken != null) {
            repository.delete(currentToken);
        }
        Token token = Token.builder()
                .token(encoder.encode(nextToken))
                .type(type)
                .username(username)
                .build();
        repository.save(token);
    }
}
