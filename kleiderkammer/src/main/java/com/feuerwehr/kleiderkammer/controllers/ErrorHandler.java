package com.feuerwehr.kleiderkammer.controllers;

import com.feuerwehr.kleiderkammer.domain.models.AdultInfo;
import com.feuerwehr.kleiderkammer.domain.models.clothes.Stuff;
import com.feuerwehr.kleiderkammer.domain.models.clothes.StuffValidator;
import com.feuerwehr.kleiderkammer.domain.repository.AdultInfoRepository;
import com.feuerwehr.kleiderkammer.domain.repository.AdultRepository;
import com.feuerwehr.kleiderkammer.domain.repository.clothes.StuffRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ErrorHandler {
    private final AdultInfoRepository adultInfoRepository;
    private final AdultRepository adultRepository;
    private final StuffRepository stuffRepository;

    public ResponseEntity<String> handleUnpairStuff(Integer id) {
        var res = stuffRepository.findById(id);
        if (res.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can not unpair stuff, this stuff dont exist");

        var stuff = res.get();
        if (stuff.getAdultClothesId() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can not unpair stuff, this stuff is not paired");


        return ResponseEntity.ok().build();
    }

    public ResponseEntity<String> handleSaveStuff(Stuff stuff) {

        if (stuffRepository.findByBatchCode(stuff.getBatchCode()).isPresent())
            return ResponseEntity.badRequest().body("Can not save stuff, this stuff batchCode is already used");

        String result = StuffValidator.validateStuff(stuff);

        if (result == null)
            return ResponseEntity.ok().build();
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public ResponseEntity<String> handleFetchStuff(Stuff stuff) {

        if (stuffRepository.findByBatchCode(stuff.getBatchCode()).isPresent() && !Objects.equals(stuffRepository.findByBatchCode(stuff.getBatchCode()).get().getId(), stuff.getId()))
            return ResponseEntity.badRequest().body("Can not fetch stuff, this stuff batchCode is already used with " +
                "another stuff");


        var _stuff = stuffRepository.findById(stuff.getId());
        if (_stuff.isEmpty())
            return ResponseEntity.badRequest().body("Can not fetch stuff, this stuff don't exist");

        var __stuff = _stuff.get();
        if (__stuff.getStuffType() != stuff.getStuffType())
            return ResponseEntity.badRequest().body("Can not fetch stuff, stuff can not change his type");

        String result = StuffValidator.validateStuff(stuff);

        if (result == null)
            return ResponseEntity.ok().build();
        else {
            var _result = result.replace("save", "fetch");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(_result);
        }
    }


    public ResponseEntity<String> handleAddStuffToUser(Integer adultId, Integer stuffId) {
        var adultOptional = adultRepository.findById(adultId);
        if (adultOptional.isEmpty())
            return ResponseEntity.badRequest().body("Can not add stuff, this adult don't exist");


        var stuffOptional = stuffRepository.findById(stuffId);

        if (stuffOptional.isEmpty())
            return ResponseEntity.badRequest().body("Can not add stuff, this stuff don't exist");


        var stuff = stuffOptional.get();

        var adult = adultOptional.get();

        if (stuff.getAdultClothesId() != null)
            return ResponseEntity.badRequest().body("Can not add stuff, this stuff already used");

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<String> handleFetchAdultInfo(AdultInfo adultInfo) {
        var adultInfoOptional = adultInfoRepository.findById(adultInfo.getId());
        if (adultInfoOptional.isEmpty())
            return ResponseEntity.badRequest().body("Can not fetch adult info, it's not exist");

        return ResponseEntity.ok().build();
    }
}
