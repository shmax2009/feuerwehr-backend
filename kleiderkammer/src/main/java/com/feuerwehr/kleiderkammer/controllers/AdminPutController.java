package com.feuerwehr.kleiderkammer.controllers;

import com.feuerwehr.kleiderkammer.domain.models.adult.Adult;
import com.feuerwehr.kleiderkammer.domain.models.adult.AdultClothes;
import com.feuerwehr.kleiderkammer.domain.models.adult.AdultInfo;
import com.feuerwehr.kleiderkammer.domain.models.dto.StuffDTO;
import com.feuerwehr.kleiderkammer.domain.models.kid.Kid;
import com.feuerwehr.kleiderkammer.domain.models.kid.KidClothes;
import com.feuerwehr.kleiderkammer.domain.models.kid.KidInfo;
import com.feuerwehr.kleiderkammer.services.StoreDeleteService;
import com.feuerwehr.kleiderkammer.services.StoreGetService;
import com.feuerwehr.kleiderkammer.services.StoreSaveService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/put")
@RequiredArgsConstructor
@Slf4j
public class AdminPutController {
    private final StoreSaveService storeSaveService;
    private final StoreGetService storeGetService;
    private final StoreDeleteService storeDeleteService;

    private final ErrorHandler errorHandler;


    @PutMapping("/stuff")
    public ResponseEntity<String> saveStuff(@RequestBody StuffDTO stuffDTO) {
        var result = errorHandler.handleSaveStuff(stuffDTO.toStuff());
        if (result.getStatusCode() != HttpStatus.OK)
            return result;
        try {
            storeSaveService.saveStuff(stuffDTO.toStuff());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return result;
    }

    @PutMapping("/stuff-adult/{adultId}/{stuffId}")
    public ResponseEntity<String> addStuffToAdult(@PathVariable(value = "adultId") Integer
                                                      adultId, @PathVariable(value = "stuffId") Integer stuffId) {
        var result = errorHandler.handleAddStuffToAdult(adultId, stuffId);
        if (result.getStatusCode() != HttpStatus.OK)
            return result;
        try {
            storeSaveService.addStuffToAdult(stuffId, adultId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }


    @PutMapping("/stuff-kid/{kidId}/{stuffId}")
    public ResponseEntity<String> addStuffToKid(@PathVariable(value = "kidId") Integer
                                                    kidId, @PathVariable(value = "stuffId") Integer stuffId) {
        var result = errorHandler.handleAddStuffToKid(kidId, stuffId);
        if (result.getStatusCode() != HttpStatus.OK)
            return result;
        try {
            storeSaveService.addStuffToKid(stuffId, kidId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }


    @PutMapping("/adult")
    public ResponseEntity<String> putAdult(@RequestBody UserCreate userCreate) {
        try {
            storeSaveService.saveAdult(new Adult(new AdultInfo(userCreate.getName(), userCreate.getSurname()),
                new AdultClothes()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/kid")
    public ResponseEntity<String> putKid(@RequestBody UserCreate userCreate) {
        try {
            storeSaveService.saveKid(new Kid(new KidInfo(userCreate.getName(), userCreate.getSurname()),
                new KidClothes()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class UserCreate {
    private String name;

    private String surname;
}
