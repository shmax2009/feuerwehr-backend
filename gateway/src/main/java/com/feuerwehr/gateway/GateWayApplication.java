package com.feuerwehr.gateway;

import com.feuerwehr.gateway.domain.entity.Role;
import com.feuerwehr.gateway.domain.entity.User;
import com.feuerwehr.gateway.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class GateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class, args);
    }



    CommandLineRunner runner(UserService userService) {
        return args -> {

            List<Role> roles = new ArrayList<>();
            roles.add(userService.addRole(Role.builder()
                    .name("ROLE_ADMIN").build()));

            userService.registerUser(User.builder()
                    .firstname("Maksym")
                    .lastname("Shvedchenko")
                    .email("maximschved8@gmail.com")
                    .username("root")
                    .password("123")
                    .roles(roles)
                    .build());

        };
    }


}