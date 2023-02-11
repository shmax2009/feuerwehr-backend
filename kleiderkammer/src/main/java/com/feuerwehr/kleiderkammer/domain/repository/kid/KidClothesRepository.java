package com.feuerwehr.kleiderkammer.domain.repository.kid;

import com.feuerwehr.kleiderkammer.domain.models.kid.KidClothes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KidClothesRepository extends JpaRepository<KidClothes, Integer> {
}