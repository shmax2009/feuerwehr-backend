package com.feierwehr.gateway;

import com.feuerwehr.gateway.auth.AuthenticationRequest;
import org.junit.jupiter.api.Test;

public class CheckAuthTest {

    @Test
    public void checkAuthorize() {
        AuthenticationRequest authenticationRequest = AuthenticationRequest.builder()
            .username("root")
            .password("123")
            .build();


    }
}
