package com.feuerwehr.kleiderkammer.controllers;

import com.feuerwehr.kleiderkammer.domain.models.dto.StuffDTO;
import com.feuerwehr.kleiderkammer.domain.models.dto.adult.AdultInfoDTO;
import com.feuerwehr.kleiderkammer.domain.models.dto.kid.KidInfoDTO;
import com.feuerwehr.kleiderkammer.services.StoreDeleteService;
import com.feuerwehr.kleiderkammer.services.StoreGetService;
import com.feuerwehr.kleiderkammer.services.StoreSaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/patch")
@RequiredArgsConstructor
@Slf4j
public class AdminFetchController {
    private final StoreSaveService storeSaveService;
    private final StoreGetService storeGetService;
    private final StoreDeleteService storeDeleteService;

    private final ErrorHandler errorHandler;
    @PatchMapping("/unpair-stuff/{id}")
    public ResponseEntity<String> unpair(@PathVariable(value = "id") Integer id) {
        var result = errorHandler.handleUnpairStuff(id);
        if (result.getStatusCode() != HttpStatus.OK)
            return result;
        try {
            storeDeleteService.unpair(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return result;
    }



    @PatchMapping("/stuff")
    public ResponseEntity<String> patchStuff(@RequestBody StuffDTO stuff) {
        var result = errorHandler.handleFetchStuff(stuff.toStuff());
        if (result.getStatusCode() != HttpStatus.OK)
            return result;
        try {
            storeSaveService.fetchStuff(stuff.toStuff());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return result;
    }

    @PatchMapping("/adultInfo")
    public ResponseEntity<String> patchAdultInfo(@RequestBody AdultInfoDTO adultInfoDTO) {
        var result = errorHandler.handleFetchAdultInfo(adultInfoDTO.toAdultInfo());
        if (result.getStatusCode() != HttpStatus.OK)
            return result;

        try {
            storeSaveService.fetchAdultInfo(adultInfoDTO.toAdultInfo());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return result;
    }


    @PatchMapping("/kidInfo")
    public ResponseEntity<String> patchAdultInfo(@RequestBody KidInfoDTO kidInfoDTO) {
        var result = errorHandler.handleFetchKidInfo(kidInfoDTO.toKidInfo());
        if (result.getStatusCode() != HttpStatus.OK)
            return result;

        try {
            storeSaveService.fetchKidInfo(kidInfoDTO.toKidInfo());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return result;
    }
}
