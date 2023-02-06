package com.feuerwehr.gateway.domain.extra;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "auth.data")
@Data
public class AuthData {
    private String secret_key;

    private Long auth_token_expiration;

    private Long refresh_token_expiration;
}
