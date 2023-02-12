package com.feuerwehr.kleiderkammer.services;

import com.feuerwehr.kleiderkammer.domain.enums.PersonType;
import com.feuerwehr.kleiderkammer.domain.models.adult.Adult;
import com.feuerwehr.kleiderkammer.domain.models.adult.AdultClothes;
import com.feuerwehr.kleiderkammer.domain.models.adult.AdultInfo;
import com.feuerwehr.kleiderkammer.domain.models.clothes.Stuff;
import com.feuerwehr.kleiderkammer.domain.models.clothes.StuffValidator;
import com.feuerwehr.kleiderkammer.domain.models.kid.Kid;
import com.feuerwehr.kleiderkammer.domain.models.kid.KidClothes;
import com.feuerwehr.kleiderkammer.domain.models.kid.KidInfo;
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

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class StoreSaveService {
    private final KidClothesRepository kidClothesRepository;
    private final KidRepository kidRepository;
    private final KidInfoRepository kidInfoRepository;
    private final AdultClothesRepository adultClothesRepository;
    private final AdultRepository adultRepository;
    private final AdultInfoRepository adultInfoRepository;
    private final StuffRepository stuffRepository;

    private final StuffValidator stuffValidator;

    void throwError(String message) throws Error {
        log.error(message);
        throw new RuntimeException(message);
    }

    public void addNewAdult(String name, String surname) {

        if (name == null) {
            throwError("Can not create new adult: his name is null");
            return;
        }

        if (surname == null) {
            throwError("Can not create new adult: his surname is null");
            return;
        }

        if (adultInfoRepository.findByNameAndSurname(name, surname).isPresent()) {
            throwError("Can not create new adult: adult with this name and surname already exist");
            return;
        }


        Adult adult = new Adult();
        AdultInfo adultInfo = new AdultInfo(name, surname);
        adult.setInfo(adultInfoRepository.save(adultInfo));
        adult.setClothes(adultClothesRepository.save(new AdultClothes()));
        adultRepository.save(adult);
    }


    public void addNewKid(String name, String surname) {

        if (name == null) {
            throwError("Can not create new kid: his name is null");
            return;
        }

        if (surname == null) {
            throwError("Can not create new kid: his surname is null");
            return;
        }

        if (kidInfoRepository.findByNameAndSurname(name, surname).isPresent()) {
            throwError("Can not create new kid: kid with this name and surname already exist");
            return;
        }


        Kid kid = new Kid();
        KidInfo kidInfo = new KidInfo(name, surname);
        kid.setInfo(kidInfoRepository.save(kidInfo));
        kid.setClothes(kidClothesRepository.save(new KidClothes()));

        kidRepository.save(kid);
    }


    public void saveStuff(Stuff stuff) {
        String conclusion = stuffValidator.validateStuffForSave(stuff);
        if (conclusion != null) {
            throwError(conclusion);
            return;
        }
        stuffRepository.save(stuff);
    }


    public void pairStuffToAdult(Integer adultId, Integer stuffId) {
        var adultOpt = adultRepository.findById(adultId);

        var stuffOpt = stuffRepository.findById(stuffId);

        if (adultOpt.isEmpty()) {
            throwError("Can not pair stuff to adult: can not find adult");
            return;
        }

        if (stuffOpt.isEmpty()) {
            throwError("Can not pair stuff to adult: can not find stuff");
            return;
        }

        var adult = adultOpt.get();

        var stuff = stuffOpt.get();

        var adultClothes = adult.getClothes();

        if (adultClothes == null) {
            throwError("Can not pair stuff to adult: this adult don't have clothes");
            return;
        }
        pairStuffToAdultClothes(adultClothes, stuff);

    }

    private void pairStuffToAdultClothes(AdultClothes clothes, Stuff stuff) {
        if (clothes.getStuff(stuff.getStuffType()) != null) {
            throwError("Can not pair stuff to adult: this adult already have this stuff");
            return;
        }

        if (stuff.getClothesId() != null) {
            throwError("Can not pair stuff to adult: this stuff is already paired");
            return;
        }

        stuff.setClothesId(clothes.getId());
        stuff.setPersonType(PersonType.Adult);
        clothes.setStuff(stuffRepository.save(stuff));
        adultClothesRepository.save(clothes);
    }

    public void pairStuffToKid(Integer kidId, Integer stuffId) {
        var kidOpt = kidRepository.findById(kidId);

        var stuffOpt = stuffRepository.findById(stuffId);

        if (kidOpt.isEmpty()) {
            throwError("Can not pair stuff to kid: can not find kid");
            return;
        }

        if (stuffOpt.isEmpty()) {
            throwError("Can not pair stuff to kid: can not find stuff");
            return;
        }

        var kid = kidOpt.get();

        var stuff = stuffOpt.get();

        var kidClothes = kid.getClothes();

        if (kidClothes == null) {
            throwError("Can not pair stuff to kid: this kid don't have clothes");
            return;
        }
        pairStuffToKidClothes(kidClothes, stuff);

    }

    private void pairStuffToKidClothes(KidClothes clothes, Stuff stuff) {
        if (clothes.getStuff(stuff.getStuffType()) != null) {
            throwError("Can not pair stuff to kid: this kid already have this stuff");
            return;
        }

        if (stuff.getClothesId() != null) {
            throwError("Can not pair stuff to kid: this stuff is already paired");
            return;
        }

        stuff.setClothesId(clothes.getId());
        clothes.setStuff(stuffRepository.save(stuff));
        stuff.setPersonType(PersonType.Kid);
        kidClothesRepository.save(clothes);
    }

    public void fetchAdultInfo(AdultInfo info) {
        if (info == null) {
            throwError("Can not fetch adult info: info is null");
            return;
        }

        if (info.getName() == null) {
            throwError("Can not fetch adult info: his name is null");
            return;
        }

        if (info.getSurname() == null) {
            throwError("Can not fetch adult info: his surname is null");
            return;
        }
        if (info.getId() == null) {
            throwError("Can not fetch adult info: can not find this adult info");
            return;
        }


        if (adultInfoRepository.findByNameAndSurname(info.getName(), info.getSurname()).isPresent()
            && !Objects.equals(adultInfoRepository.findByNameAndSurname(info.getName(), info.getSurname()).get().getId(), info.getId())) {
            throwError("Can not fetch adult info: adult with this name and surname already exist");
            return;
        }
        if (!adultInfoRepository.existsById(info.getId()))
            throwError("Can not fetch adult info: he don't exist");
        adultInfoRepository.save(info);
    }

    public void fetchKidInfo(KidInfo info) {
        if (info == null) {
            throwError("Can not fetch kid info: info is null");
            return;
        }

        if (info.getName() == null) {
            throwError("Can not fetch kid info: his name is null");
            return;
        }

        if (info.getSurname() == null) {
            throwError("Can not fetch kid info: his surname is null");
            return;
        }
        if (info.getId() == null) {
            throwError("Can not fetch kid info: can not find this kid info");
            return;
        }


        if (adultInfoRepository.findByNameAndSurname(info.getName(), info.getSurname()).isPresent()
            && !Objects.equals(adultInfoRepository.findByNameAndSurname(info.getName(), info.getSurname()).get().getId(), info.getId())) {
            throwError("Can not fetch kid info: kid with this name and surname already exist");
            return;
        }


        if (!kidInfoRepository.existsById(info.getId()))
            throwError("Can not fetch kid info: he don't exist");

        kidInfoRepository.save(info);
    }

    public void fetchStuff(Stuff stuff) {
        String conclusion = stuffValidator.validateStuffForFetch(stuff);
        if (conclusion != null) {
            throwError(conclusion);
            return;
        }
        stuffRepository.save(stuff);
    }
}
