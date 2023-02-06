package com.feuerwehr.gateway.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role getRoleByName(String name);
}
