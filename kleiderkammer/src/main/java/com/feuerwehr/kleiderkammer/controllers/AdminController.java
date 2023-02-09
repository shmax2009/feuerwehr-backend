package com.feuerwehr.kleiderkammer.controllers;


import com.feuerwehr.kleiderkammer.domain.models.Adult;
import com.feuerwehr.kleiderkammer.domain.models.AdultClothes;
import com.feuerwehr.kleiderkammer.domain.models.AdultInfo;
import com.feuerwehr.kleiderkammer.domain.models.dto.AdultDTO;
import com.feuerwehr.kleiderkammer.domain.models.dto.AdultInfoDTO;
import com.feuerwehr.kleiderkammer.domain.models.dto.StuffDTO;
import com.feuerwehr.kleiderkammer.services.StoreDeleteService;
import com.feuerwehr.kleiderkammer.services.StoreGetService;
import com.feuerwehr.kleiderkammer.services.StoreSaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {
    private final StoreSaveService storeSaveService;
    private final StoreGetService storeGetService;
    private final StoreDeleteService storeDeleteService;

    private final ErrorHandler errorHandler;

    //
    @GetMapping("/get/adults")
    public List<AdultDTO> getAdults() {
        return storeGetService.getAdults().stream().map(AdultDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/get/stuffs")
    public List<StuffDTO> getStuffs() throws Exception {

//        throw new Exception("Bla bla bla");
        return storeGetService.getStuffs().stream().map(StuffDTO::new).collect(Collectors.toList());
    }

    @PatchMapping("/patch/unpair-stuff/{id}")
    public ResponseEntity<String> unpair(@PathVariable(value = "id") Integer id) {
        var result = errorHandler.handleUnpairStuff(id);
        if (result.getStatusCode() != HttpStatus.OK)
            return result;
        storeDeleteService.unpair(id);
        return result;
    }

    @PutMapping("/put/stuff")
    public ResponseEntity<String> saveStuff(@RequestBody StuffDTO stuffDTO) {
        var result = errorHandler.handleSaveStuff(stuffDTO.toStuff());
        if (result.getStatusCode() != HttpStatus.OK)
            return result;
        storeSaveService.saveStuff(stuffDTO.toStuff());
        return result;
    }

    @PutMapping("/put/stuff-user/{adultId}/{stuffId}")
    public ResponseEntity<String> addStuffToUser(@PathVariable(value = "adultId") Integer adultId, @PathVariable(value = "stuffId") Integer stuffId) {
        var result = errorHandler.handleAddStuffToUser(adultId, stuffId);
        if (result.getStatusCode() != HttpStatus.OK)
            return result;
        storeSaveService.addStuffToAdult(stuffId, adultId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/patch/stuff")
    public ResponseEntity<String> patchStuff(@RequestBody StuffDTO stuff) {
        var result = errorHandler.handleFetchStuff(stuff.toStuff());
        if (result.getStatusCode() != HttpStatus.OK)
            return result;
        storeSaveService.fetchStuff(stuff.toStuff());
        return result;
    }

    @PatchMapping("/patch/adultInfo")
    public ResponseEntity<String> patchAdultInfo(@RequestBody AdultInfoDTO adultInfoDTO) {
        var result = errorHandler.handleFetchAdultInfo(adultInfoDTO.toAdultInfo());
        if (result.getStatusCode() != HttpStatus.OK)
            return result;
        storeSaveService.fetchAdultInfo(adultInfoDTO.toAdultInfo());
        return result;
    }


    @PutMapping("/put/adult")
    public ResponseEntity<String> putAdult() {
        storeSaveService.saveAdult(new Adult(new AdultInfo(), new AdultClothes()));
        return ResponseEntity.ok().build();
    }
//
//
//    @GetMapping("/check")
//    public boolean check() {
//        return true;
//    }

}

