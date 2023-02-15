package com.feuerwehr.kleiderkammer.controllers.admin;

import com.feuerwehr.kleiderkammer.domain.models.dto.StuffDTO;
import com.feuerwehr.kleiderkammer.domain.models.dto.adult.AdultInfoDTO;
import com.feuerwehr.kleiderkammer.domain.models.dto.kid.KidInfoDTO;
import com.feuerwehr.kleiderkammer.services.StoreDeleteService;
import com.feuerwehr.kleiderkammer.services.StoreGetService;
import com.feuerwehr.kleiderkammer.services.StoreSaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    @PatchMapping("/unpair-stuff/{id}")
    public ResponseEntity<String> unpair(@PathVariable(value = "id") Integer id) {

        try {
            storeDeleteService.unpairStuff(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }



    @PatchMapping("/stuff")
    public ResponseEntity<String> patchStuff(@RequestBody StuffDTO stuff) {
        try {
            storeSaveService.fetchStuff(stuff.toStuff());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/adultInfo")
    public ResponseEntity<String> patchAdultInfo(@RequestBody AdultInfoDTO adultInfoDTO) {
        try {
            storeSaveService.fetchAdultInfo(adultInfoDTO.toAdultInfo());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }


    @PatchMapping("/kidInfo")
    public ResponseEntity<String> patchAdultInfo(@RequestBody KidInfoDTO kidInfoDTO) {


        try {
            storeSaveService.fetchKidInfo(kidInfoDTO.toKidInfo());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
