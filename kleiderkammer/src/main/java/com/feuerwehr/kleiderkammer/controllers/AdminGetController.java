package com.feuerwehr.kleiderkammer.controllers;

import com.feuerwehr.kleiderkammer.domain.models.dto.StuffDTO;
import com.feuerwehr.kleiderkammer.domain.models.dto.adult.AdultDTO;
import com.feuerwehr.kleiderkammer.domain.models.dto.kid.KidDTO;
import com.feuerwehr.kleiderkammer.services.StoreDeleteService;
import com.feuerwehr.kleiderkammer.services.StoreGetService;
import com.feuerwehr.kleiderkammer.services.StoreSaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/get")
@RequiredArgsConstructor
@Slf4j
public class AdminGetController {
    private final StoreSaveService storeSaveService;
    private final StoreGetService storeGetService;
    private final StoreDeleteService storeDeleteService;

    private final ErrorHandler errorHandler;


    @GetMapping("/adults")
    public List<AdultDTO> getAdults() {
        return storeGetService.getAdults().stream().map(AdultDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/stuffs")
    public List<StuffDTO> getStuffs() {
        return storeGetService.getStuffs().stream().map(StuffDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/kids")
    public List<KidDTO> getKids() {
        return storeGetService.getKids().stream().map(KidDTO::new).collect(Collectors.toList());
    }
}
