package com.feuerwehr.kleiderkammer.domain.repository.kid;

import com.feuerwehr.kleiderkammer.domain.models.kid.KidInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface KidInfoRepository extends JpaRepository<KidInfo, Integer> {
    Optional<KidInfo> findByNameAndSurname(String name, String surname);
}