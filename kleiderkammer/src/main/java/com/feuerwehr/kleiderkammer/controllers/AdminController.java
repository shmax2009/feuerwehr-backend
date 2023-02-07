package com.feuerwehr.kleiderkammer.controllers;


import com.feuerwehr.kleiderkammer.domain.models.Adult;
import com.feuerwehr.kleiderkammer.domain.models.clothes.Stuff;
import com.feuerwehr.kleiderkammer.services.StoreDeleteService;
import com.feuerwehr.kleiderkammer.services.StoreGetService;
import com.feuerwehr.kleiderkammer.services.StoreSaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Adult> getAdults() {
        return storeGetService.getAdults();
    }

    @GetMapping("/get/stuffs")
    public List<Stuff> getStuffs() {
        return storeGetService.getStuffs();
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
    public ResponseEntity<String> saveStuff(@RequestBody Stuff stuff) {
        var result = errorHandler.handleSaveStuff(stuff);
        if (result.getStatusCode() != HttpStatus.OK)
            return result;
        storeSaveService.saveStuff(stuff);
        return result;
    }

    @PutMapping("/put/stuff-user/{adultId}/{stuffId}")
    public ResponseEntity<String> addStuffToUser(@PathVariable(value = "adultId") Integer adultId, @PathVariable(value = "stuffId") Integer stuffId) {
        var result = errorHandler.handleAddStuffToUser(adultId, stuffId);
        if (result.getStatusCode() != HttpStatus.OK)
            return result;
        String s = storeSaveService.addStuffToAdult(stuffId, adultId);
        if (s == null)
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(s);
    }
//
//
//    @PostMapping("/add/helmet")
//    public ResponseEntity saveHelmet(@RequestBody Helmet helmet) {
//        storeSaveService.saveStuff(helmet);
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/add/jacket")
//    public ResponseEntity saveJacket(@RequestBody Jacket jacket) {
//        storeSaveService.saveStuff(jacket);
//        return ResponseEntity.ok().build();
//    }
//
//
//    @GetMapping("/check")
//    public boolean check() {
//        return true;
//    }

}

