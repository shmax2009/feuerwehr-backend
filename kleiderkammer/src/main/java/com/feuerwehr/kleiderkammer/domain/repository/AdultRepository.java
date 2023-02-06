package com.feuerwehr.kleiderkammer.domain.repository;

import com.feuerwehr.kleiderkammer.domain.models.Adult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdultRepository extends JpaRepository<Adult, Long> {
    Optional<Adult> findByAdultInfo_NameAndAdultInfo_Surname(String adultInfo_name, String adultInfo_surname);

    Optional<Adult> findById(Integer id);
}
