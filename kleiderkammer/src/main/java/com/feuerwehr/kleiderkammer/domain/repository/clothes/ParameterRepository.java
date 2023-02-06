package com.feuerwehr.kleiderkammer.domain.repository.clothes;

import com.feuerwehr.kleiderkammer.domain.models.clothes.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParameterRepository extends JpaRepository<Parameter, Integer> {
}