package com.feuerwehr.gateway.services;

import com.feuerwehr.gateway.domain.entity.Role;
import com.feuerwehr.gateway.domain.entity.RoleRepository;
import com.feuerwehr.gateway.domain.entity.User;
import com.feuerwehr.gateway.domain.entity.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    boolean checkUserDetails(User user) {
        if (user == null) {
            log.error("Can not register user: user is null");
            return false;
        }

        if (user.getUsername() == null) {
            log.error("Can not register user: username is null");
            return false;
        }
        if (user.getEmail() == null) {
            log.error("Can not register user: email is null");
            return false;
        }
        if (user.getPassword() == null) {
            log.error("Can not register user: password is null");
            return false;
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            log.error("Can not register user: email is already used by another user");
            return false;
        }

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            log.error("Can not register user: email is already used by another user");
            return false;
        }
        return true;
    }

    public User registerUser(User user) {
        if (!checkUserDetails(user))
            return null;
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    public Role addRole(Role role) {
        if (role.getName() != null && roleRepository.getRoleByName(role.getName()) == null) {
            return roleRepository.save(role);
        }
        return null;
    }


    public List<User> getUsers() {
        return userRepository.findAll();
    }

}
