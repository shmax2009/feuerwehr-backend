package com.feuerwehr.kleiderkammer.services;


import com.feuerwehr.kleiderkammer.domain.enums.PersonType;
import com.feuerwehr.kleiderkammer.domain.models.adult.Adult;
import com.feuerwehr.kleiderkammer.domain.models.clothes.Stuff;
import com.feuerwehr.kleiderkammer.domain.models.kid.Kid;
import com.feuerwehr.kleiderkammer.domain.repository.adult.AdultClothesRepository;
import com.feuerwehr.kleiderkammer.domain.repository.adult.AdultInfoRepository;
import com.feuerwehr.kleiderkammer.domain.repository.adult.AdultRepository;
import com.feuerwehr.kleiderkammer.domain.repository.clothes.StuffRepository;
import com.feuerwehr.kleiderkammer.domain.repository.kid.KidClothesRepository;
import com.feuerwehr.kleiderkammer.domain.repository.kid.KidInfoRepository;
import com.feuerwehr.kleiderkammer.domain.repository.kid.KidRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class StoreDeleteService {
    private final KidInfoRepository kidInfoRepository;
    private final KidRepository kidRepository;
    private final KidClothesRepository kidClothesRepository;
    private final AdultClothesRepository adultClothesRepository;
    private final AdultRepository adultRepository;
    private final AdultInfoRepository adultInfoRepository;
    private final StuffRepository stuffRepository;

    void throwError(String message) throws Error {
        log.error(message);
        throw new RuntimeException(message);
    }


    //    Stuff is always normal because its from database
    private void unpairStuff(Optional<Stuff> stuffOptional) {
        if (stuffOptional.isEmpty()) {
//            throwError("Can not unpair stuff, it's null");
            return;
        }
        var stuff = stuffOptional.get();

        if (stuff.getPersonType() == null) {
            throwError("Can not unpair stuff,it's not paired");
            return;
        }
        if (stuff.getClothesId() == null) {
            throwError("Can not unpair stuff,it's not paired");
            return;
        }


        if (stuff.getPersonType() == PersonType.Adult) {
            var adultClothesOpt = adultClothesRepository.findById(stuff.getClothesId());
            if (adultClothesOpt.isEmpty()) {
                throwError("Something went wrong");
                return;
            }
            var adultClothes = adultClothesOpt.get();
            adultClothes.setStuff(null, stuff.getStuffType());
            adultClothesRepository.save(adultClothes);
            stuff.setPersonType(null);
            stuff.setClothesId(null);
            stuffRepository.save(stuff);
        } else if (stuff.getPersonType() == PersonType.Kid) {
            var kidClothesOpt = kidClothesRepository.findById(stuff.getClothesId());
            if (kidClothesOpt.isEmpty()) {
                throwError("Something went wrong");
                return;
            }
            var kidClothes = kidClothesOpt.get();
            kidClothes.setStuff(null, stuff.getStuffType());
            kidClothesRepository.save(kidClothes);
            stuff.setPersonType(null);
            stuff.setClothesId(null);
            stuffRepository.save(stuff);
        }
    }

    public void unpairStuff(Integer stuffId) {
        unpairStuff(stuffRepository.findById(stuffId));
    }


    private void deleteAdult(Optional<Adult> adultOptional) {
        if (adultOptional.isEmpty()) {
            throwError("Can not delete adult,he don't exist");
            return;
        }

        var adult = adultOptional.get();

        adultInfoRepository.delete(adult.getInfo());
        var adultClothes = adult.getClothes();
        unpairStuff(Optional.ofNullable(adultClothes.getGloves()));
        unpairStuff(Optional.ofNullable(adultClothes.getHelmet()));
        unpairStuff(Optional.ofNullable(adultClothes.getCombatJacket()));
        unpairStuff(Optional.ofNullable(adultClothes.getBelt()));
        unpairStuff(Optional.ofNullable(adultClothes.getFirefightingBoots()));
        unpairStuff(Optional.ofNullable(adultClothes.getFirefightingGloves()));
        unpairStuff(Optional.ofNullable(adultClothes.getTopTrousers()));
        unpairStuff(Optional.ofNullable(adultClothes.getCombatTrousers()));
//        TODO
        adultClothesRepository.delete(adultClothes);

        adultRepository.delete(adult);

    }

    public void deleteAdult(Integer adultId) {
        deleteAdult(adultRepository.findById(adultId));
    }


    public void deleteStuff(Integer stuffId) {
        Optional<Stuff> stuff = stuffRepository.findById(stuffId);
        if (stuff.isEmpty()) {
            throwError("Can not delete stuff, he is null");
            return;
        }
        Stuff s = stuff.get();
        if (s.getClothesId() != null)
            unpairStuff(stuff);

        stuffRepository.delete(s);
    }


    private void deleteKid(Optional<Kid> kidOptional) {
        if (kidOptional.isEmpty()) {
            throwError("Can not delete kid,he don't exist");
            return;
        }

        var kid = kidOptional.get();

        kidInfoRepository.delete(kid.getInfo());
        var kidClothes = kid.getClothes();
        unpairStuff(Optional.ofNullable(kidClothes.getHat()));
        unpairStuff(Optional.ofNullable(kidClothes.getJacket()));
        unpairStuff(Optional.ofNullable(kidClothes.getCap()));
        unpairStuff(Optional.ofNullable(kidClothes.getPolo()));
        unpairStuff(Optional.ofNullable(kidClothes.getParka()));
        unpairStuff(Optional.ofNullable(kidClothes.getPullover()));
        unpairStuff(Optional.ofNullable(kidClothes.getGloves()));
        unpairStuff(Optional.ofNullable(kidClothes.getTrousers()));
        unpairStuff(Optional.ofNullable(kidClothes.getFirefightingBoots()));
        unpairStuff(Optional.ofNullable(kidClothes.getTShirt()));
        unpairStuff(Optional.ofNullable(kidClothes.getSoftShellJacket()));
        unpairStuff(Optional.ofNullable(kidClothes.getCap()));

//        TODO
        kidClothesRepository.delete(kidClothes);

        kidRepository.delete(kid);

    }

    public void deleteKid(Integer kidId) {
        deleteKid(kidRepository.findById(kidId));
    }

}
