package com.feuerwehr.kleiderkammer.domain.repository.clothes;

import com.feuerwehr.kleiderkammer.domain.models.clothes.Stuff;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StuffRepository extends JpaRepository<Stuff, Integer> {

    @NotNull Optional<Stuff> findByBatchCode(@NotNull Integer batchCode);


    @NotNull Optional<Stuff> findById(@NotNull Integer id);


}