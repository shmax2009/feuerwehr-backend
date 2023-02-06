package com.feuerwehr.kleiderkammer.services;

import com.feuerwehr.kleiderkammer.domain.repository.AdultClothesRepository;
import com.feuerwehr.kleiderkammer.domain.repository.AdultInfoRepository;
import com.feuerwehr.kleiderkammer.domain.repository.AdultRepository;
import com.feuerwehr.kleiderkammer.domain.repository.clothes.ParameterRepository;
import com.feuerwehr.kleiderkammer.domain.repository.clothes.StuffRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class StoreUpdateService {
    private final ParameterRepository parameterRepository;
    private final AdultClothesRepository adultClothesRepository;
    private final AdultRepository adultRepository;
    private final AdultInfoRepository adultInfoRepository;
    private final StuffRepository stuffRepository;


}