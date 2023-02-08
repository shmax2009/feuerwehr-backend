package com.feuerwehr.kleiderkammer.services;

import com.feuerwehr.kleiderkammer.domain.models.Adult;
import com.feuerwehr.kleiderkammer.domain.models.clothes.Stuff;
import com.feuerwehr.kleiderkammer.domain.repository.AdultClothesRepository;
import com.feuerwehr.kleiderkammer.domain.repository.AdultInfoRepository;
import com.feuerwehr.kleiderkammer.domain.repository.AdultRepository;
import com.feuerwehr.kleiderkammer.domain.repository.clothes.StuffRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class StoreGetService {
    private final AdultClothesRepository adultClothesRepository;
    private final AdultRepository adultRepository;
    private final AdultInfoRepository adultInfoRepository;
    private final StuffRepository stuffRepository;

    public List<Adult> getAdults() {
        return adultRepository.findAll();
    }

    public List<Stuff> getStuffs() {
        return stuffRepository.findAll();
    }


}
