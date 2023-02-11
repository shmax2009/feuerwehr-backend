package com.feuerwehr.kleiderkammer.domain.repository.kid;

import com.feuerwehr.kleiderkammer.domain.models.kid.Kid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KidRepository extends JpaRepository<Kid, Integer> {
}