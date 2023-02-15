package com.feuerwehr.kleiderkammer.controllers.admin;


import com.feuerwehr.kleiderkammer.services.StoreDeleteService;
import com.feuerwehr.kleiderkammer.services.StoreGetService;
import com.feuerwehr.kleiderkammer.services.StoreSaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/delete")
@RequiredArgsConstructor
@Slf4j
public class AdminDeleteController {
    private final StoreSaveService storeSaveService;
    private final StoreGetService storeGetService;
    private final StoreDeleteService storeDeleteService;


    @DeleteMapping("/stuff/{stuffId}")
    public ResponseEntity<String> deleteStuff(@PathVariable(value = "stuffId") Integer stuffId) {
        try {
            storeDeleteService.deleteStuff(stuffId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/adult/{adultId}")
    public ResponseEntity<String> deleteAdult(@PathVariable(value = "adultId") Integer adultId) {
        try {
            storeDeleteService.deleteAdult(adultId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/kid/{kidId}")
    public ResponseEntity<String> deleteKid(@PathVariable(value = "kidId") Integer kidId) {
        try {
            storeDeleteService.deleteKid(kidId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }



}


