package com.feuerwehr.kleiderkammer.domain.repository.adult;

import com.feuerwehr.kleiderkammer.domain.models.adult.AdultVerein;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdultVereinRepository extends JpaRepository<AdultVerein, Integer> {
}