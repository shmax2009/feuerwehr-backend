package com.feuerwehr.kleiderkammer.domain.repository.adult;

import com.feuerwehr.kleiderkammer.domain.models.adult.Adult;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdultRepository extends JpaRepository<Adult, Integer> {
    Optional<Adult> findByInfo_NameAndInfo_Surname(String info_name, String info_surname);

    @NotNull Optional<Adult> findById(@NotNull Integer id);
}
