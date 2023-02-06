package com.feuerwehr.kleiderkammer.controllers;


import com.feuerwehr.kleiderkammer.domain.models.Adult;
import com.feuerwehr.kleiderkammer.services.StoreDeleteService;
import com.feuerwehr.kleiderkammer.services.StoreGetService;
import com.feuerwehr.kleiderkammer.services.StoreSaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    //
    @GetMapping("/get/adults")
    public List<Adult> getAdults() {
        return storeGetService.getAdults();
    }

    @PatchMapping("/patch/unpair-stuff/{id}")
    public void unpair(@PathVariable(value = "id") Integer id) {
        storeDeleteService.unpair(id);
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

