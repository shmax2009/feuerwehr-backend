package com.feuerwehr.kleiderkammer.domain.repository.adult;

import com.feuerwehr.kleiderkammer.domain.models.adult.AdultClothes;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdultClothesRepository extends JpaRepository<AdultClothes, Integer> {

    @NotNull Optional<AdultClothes> findById(@NotNull Integer id);


}
