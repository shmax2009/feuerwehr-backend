package com.feuerwehr.kleiderkammer.domain.repository.adult;

import com.feuerwehr.kleiderkammer.domain.models.adult.AdultInfo;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdultInfoRepository extends JpaRepository<AdultInfo, Integer> {

    @NotNull Optional<AdultInfo> findById(@NotNull Integer id);

    Optional<AdultInfo> findByNameAndSurname(String name, String surname);

}
