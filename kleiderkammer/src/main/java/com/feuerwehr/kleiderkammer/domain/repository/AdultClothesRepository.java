package com.feuerwehr.kleiderkammer.domain.repository;

import com.feuerwehr.kleiderkammer.domain.models.AdultClothes;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdultClothesRepository extends JpaRepository<AdultClothes, Long> {

    @NotNull Optional<AdultClothes> findById(@NotNull Integer id);


}
