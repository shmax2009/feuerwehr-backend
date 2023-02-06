package com.feuerwehr.gateway.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {


    void removeByToken(String token);

    Token getByUsernameAndType(String username, Type type);


}
