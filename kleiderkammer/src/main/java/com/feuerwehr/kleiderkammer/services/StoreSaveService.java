package com.feuerwehr.kleiderkammer.services;

import com.feuerwehr.kleiderkammer.domain.models.Adult;
import com.feuerwehr.kleiderkammer.domain.models.AdultClothes;
import com.feuerwehr.kleiderkammer.domain.models.AdultInfo;
import com.feuerwehr.kleiderkammer.domain.models.clothes.Stuff;
import com.feuerwehr.kleiderkammer.domain.repository.AdultClothesRepository;
import com.feuerwehr.kleiderkammer.domain.repository.AdultInfoRepository;
import com.feuerwehr.kleiderkammer.domain.repository.AdultRepository;
import com.feuerwehr.kleiderkammer.domain.repository.clothes.StuffRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class StoreSaveService {
    private final AdultClothesRepository adultClothesRepository;
    private final AdultRepository adultRepository;
    private final AdultInfoRepository adultInfoRepository;
    private final StuffRepository stuffRepository;

    public Adult saveAdult(@NotNull Adult adult) {
        if (adultRepository.findByAdultInfo_NameAndAdultInfo_Surname(adult.getAdultInfo().getName(),
            adult.getAdultInfo().getSurname()).isPresent()) {
            log.warn("This adult is already exist");
            return adultRepository.findByAdultInfo_NameAndAdultInfo_Surname(adult.getAdultInfo().getName(),
                adult.getAdultInfo().getSurname()).get();
        }

        if (adult.getAdultInfo() == null) {
            log.warn("This adult have no personal information");
            return null;
        }

        if (adultInfoRepository.findByNameAndSurname(adult.getAdultInfo().getName(),
            adult.getAdultInfo().getSurname()).isEmpty()) {
            adult.setAdultInfo(saveAdultInfo(adult.getAdultInfo()));
        }

        if (adult.getAdultClothes() != null) {
            adult.setAdultClothes(saveAdultClothes(adult.getAdultClothes()));
        }


        return adultRepository.save(adult);
    }

    public AdultInfo saveAdultInfo(AdultInfo adultInfo) {
        return adultInfoRepository.save(adultInfo);
    }

    public AdultClothes saveAdultClothes(AdultClothes adultClothes) {

        var skeleton = adultClothesRepository.save(AdultClothes.builder().build());

        addStuffToClothes(adultClothes.getHelmet(), skeleton);
        addStuffToClothes(adultClothes.getBelt(), skeleton);
        addStuffToClothes(adultClothes.getGloves(), skeleton);
        addStuffToClothes(adultClothes.getCombatJacket(), skeleton);
        addStuffToClothes(adultClothes.getCombatTrousers(), skeleton);
        addStuffToClothes(adultClothes.getFirefightingBoots(), skeleton);
        addStuffToClothes(adultClothes.getFirefightingGloves(), skeleton);
        addStuffToClothes(adultClothes.getTopTrousers(), skeleton);

        return adultClothesRepository.save(skeleton);
    }

    void addStuffToAdult(Stuff stuff, Adult adult) {
        if (adult == null) {
            log.warn("Can not add stuff to user : user is null");
            throw new RuntimeException("Can not add stuff to user : user is null");
        }
        if (adult.getAdultClothes() == null) {
            log.warn("Can not add stuff to user : user clothes is null");
            throw new RuntimeException("Can not add stuff to user : user clothes is null");
        }

        addStuffToClothes(stuff, adult.getAdultClothes());
        adultClothesRepository.save(adult.getAdultClothes());
    }

    public void addStuffToAdult(Integer stuffId, Integer adultId) {
        var stuff = stuffRepository.findById(stuffId);
        var adult = adultRepository.findById(adultId);
        if (stuff.isPresent() && adult.isPresent()) {
            addStuffToAdult(stuff.get(), adult.get());
            return;
        }
        throw new RuntimeException("Something went wrong");
    }

    void addStuffToClothes(Stuff stuff, AdultClothes clothes) {
        if (stuff == null)
            return;
        switch (stuff.getStuffType()) {
            case Helm -> {

                if (clothes.getHelmet() != null) {
                    log.warn("Can not add: This adult already have " + stuff.getStuffType().name());
                    throw new RuntimeException("Can not add: This adult already have " + stuff.getStuffType().name());
                }
                var _stuff = saveStuffToClothes(stuff, clothes);

                clothes.setHelmet(_stuff);
            }
            case Einsatzjacke -> {

                if (clothes.getCombatJacket() != null) {
                    log.warn("Can not add: This adult already have " + stuff.getStuffType().name());
                    throw new RuntimeException("Can not add: This adult already have " + stuff.getStuffType().name());
                }
                var _stuff = saveStuffToClothes(stuff, clothes);

                clothes.setCombatJacket(_stuff);
            }
            case Einsatzhose -> {

                if (clothes.getCombatTrousers() != null) {
                    log.warn("Can not add: This adult already have " + stuff.getStuffType().name());
                    throw new RuntimeException("Can not add: This adult already have " + stuff.getStuffType().name());
                }
                var _stuff = saveStuffToClothes(stuff, clothes);

                clothes.setCombatTrousers(_stuff);
            }
            case Überhose -> {

                if (clothes.getTopTrousers() != null) {
                    log.warn("Can not add: This adult already have " + stuff.getStuffType().name());
                    throw new RuntimeException("Can not add: This adult already have " + stuff.getStuffType().name());
                }
                var _stuff = saveStuffToClothes(stuff, clothes);

                clothes.setTopTrousers(_stuff);
            }
            case Feuerwehrstiefel -> {

                if (clothes.getFirefightingBoots() != null) {
                    log.warn("Can not add: This adult already have " + stuff.getStuffType().name());
                    throw new RuntimeException("Can not add: This adult already have " + stuff.getStuffType().name());
                }
                var _stuff = saveStuffToClothes(stuff, clothes);

                clothes.setFirefightingBoots(_stuff);
            }
            case Gurt -> {

                if (clothes.getBelt() != null) {
                    log.warn("Can not add: This adult already have " + stuff.getStuffType().name());
                    throw new RuntimeException("Can not add: This adult already have " + stuff.getStuffType().name());
                }
                var _stuff = saveStuffToClothes(stuff, clothes);

                clothes.setBelt(_stuff);
            }
            case HandschuheBrandbekämpfung -> {

                if (clothes.getFirefightingGloves() != null) {
                    log.warn("Can not add: This adult already have " + stuff.getStuffType().name());
                    throw new RuntimeException("Can not add: This adult already have " + stuff.getStuffType().name());
                }
                var _stuff = saveStuffToClothes(stuff, clothes);

                clothes.setFirefightingGloves(_stuff);
            }
            case Handschuhe -> {

                if (clothes.getGloves() != null) {
                    log.warn("Can not add: This adult already have " + stuff.getStuffType().name());
                    throw new RuntimeException("Can not add: This adult already have " + stuff.getStuffType().name());
                }
                var _stuff = saveStuffToClothes(stuff, clothes);

                clothes.setGloves(_stuff);
            }
        }

    }

    Stuff saveStuffToClothes(Stuff stuff, AdultClothes clothes) {
        if (stuff == null)
            return null;

        var stuffOptional = stuffRepository.findByBatchCode(stuff.getBatchCode());
        if (stuffOptional.isEmpty()) {
            stuff.setAdultClothesId(clothes.getId());
            return saveStuff(stuff);
        } else {
            var _stuff = stuffOptional.get();
            if (_stuff.getAdultClothesId() != null) {
                log.error("This " + stuff.getStuffType().name() + " already used");
                throw new RuntimeException("This " + stuff.getStuffType().name() + " already used");
            }
            _stuff.setAdultClothesId(clothes.getId());
            return stuffRepository.save(_stuff);

        }
    }

    public Stuff saveStuff(Stuff stuff) {
        if (stuff == null)
            return null;
        if (stuffRepository.findByBatchCode(stuff.getBatchCode()).isPresent()) {
            log.warn("Can not save stuff: already exist");
            return null;
        }

        return stuffRepository.save(stuff);
    }


    public Stuff fetchStuff(Stuff stuff) {
        if (stuff == null)
            return null;
        if (stuffRepository.findById(stuff.getId()).isEmpty())
            throw new RuntimeException("Something went wrong");
        stuff.setAdultClothesId(stuffRepository.findById(stuff.getId()).get().getAdultClothesId());
        return stuffRepository.save(stuff);
    }


}
