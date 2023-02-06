package com.feuerwehr.kleiderkammer.domain.repository;

import com.feuerwehr.kleiderkammer.domain.models.AdultInfo;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdultInfoRepository extends JpaRepository<AdultInfo, Long> {

    @NotNull Optional<AdultInfo> findById(@NotNull Integer id);

    Optional<AdultInfo> findByNameAndSurname(String name, String surname);

}
