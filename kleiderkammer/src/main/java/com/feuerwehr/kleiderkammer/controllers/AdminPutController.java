package com.feuerwehr.kleiderkammer.controllers;

import com.feuerwehr.kleiderkammer.domain.models.dto.StuffDTO;
import com.feuerwehr.kleiderkammer.services.StoreDeleteService;
import com.feuerwehr.kleiderkammer.services.StoreGetService;
import com.feuerwehr.kleiderkammer.services.StoreSaveService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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



    @PutMapping("/stuff")
    public ResponseEntity<String> saveStuff(@RequestBody StuffDTO stuffDTO) {
        try {
            storeSaveService.saveStuff(stuffDTO.toStuff());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/stuff-adult/{adultId}/{stuffId}")
    public ResponseEntity<String> addStuffToAdult(@PathVariable(value = "adultId") Integer
                                                      adultId, @PathVariable(value = "stuffId") Integer stuffId) {
        try {
            storeSaveService.pairStuffToAdult(adultId, stuffId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }


    @PutMapping("/stuff-kid/{kidId}/{stuffId}")
    public ResponseEntity<String> addStuffToKid(@PathVariable(value = "kidId") Integer
                                                    kidId, @PathVariable(value = "stuffId") Integer stuffId) {
        try {
            storeSaveService.pairStuffToKid(kidId, stuffId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }


    @PutMapping("/adult")
    public ResponseEntity<String> putAdult(@RequestBody UserCreate userCreate) {
        try {
            storeSaveService.addNewAdult(userCreate.getName(), userCreate.getSurname());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/kid")
    public ResponseEntity<String> putKid(@RequestBody UserCreate userCreate) {
        try {
            storeSaveService.addNewKid(userCreate.getName(), userCreate.getSurname());
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
