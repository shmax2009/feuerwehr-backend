package com.feuerwehr.kleiderkammer.services;

import com.feuerwehr.kleiderkammer.domain.models.adult.Adult;
import com.feuerwehr.kleiderkammer.domain.models.adult.AdultClothes;
import com.feuerwehr.kleiderkammer.domain.models.adult.AdultInfo;
import com.feuerwehr.kleiderkammer.domain.models.clothes.Stuff;
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
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

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

    public Adult saveAdult(@NotNull Adult adult) {

        if (adult.getInfo() == null) {
            log.error("This adult have no personal information");
            throw new RuntimeException("This adult have no personal information");
        }

        if (adult.getInfo().getSurname() == null) {
            log.error("This adult dont have surname");
            throw new RuntimeException("This adult dont have surname");
        }
        if (adult.getInfo().getName() == null) {
            log.error("This adult dont have name");
            throw new RuntimeException("This adult dont have name");
        }

        if (adultInfoRepository.findByNameAndSurname(adult.getInfo().getName(),
            adult.getInfo().getSurname()).isEmpty()) {
            adult.setInfo(saveAdultInfo(adult.getInfo()));
        } else {
            throw new RuntimeException("Can not create an adult this name and surname already used");
        }

        if (adult.getClothes() == null)
            adult.setClothes(new AdultClothes());

        adult.setClothes(saveAdultClothes(adult.getClothes()));


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
        if (adult.getClothes() == null) {
            log.warn("Can not add stuff to user : user clothes is null");
            throw new RuntimeException("Can not add stuff to user : user clothes is null");
        }

        addStuffToClothes(stuff, adult.getClothes());
        adultClothesRepository.save(adult.getClothes());
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

        if (clothes.getStuff(stuff.getStuffType()) != null) {
            log.warn("Can not add: This adult already have " + stuff.getStuffType().name());
            throw new RuntimeException("Can not add: This adult already have " + stuff.getStuffType().name());
        }

        var _stuff = saveStuffToClothes(stuff, clothes.getId());
        clothes.setStuff(stuff);
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
        stuff.setClothesId(stuffRepository.findById(stuff.getId()).get().getClothesId());
        return stuffRepository.save(stuff);
    }

    public void fetchAdultInfo(AdultInfo adultInfo) {
        if (adultInfo == null)
            return;
        if (adultInfoRepository.findById(adultInfo.getId()).isEmpty())
            throw new RuntimeException("Something went wrong");
        adultInfoRepository.save(adultInfo);
    }


    public Kid saveKid(@NotNull Kid kid) {

        if (kid.getInfo() == null) {
            log.error("This kid have no personal information");
            throw new RuntimeException("This kid have no personal information");
        }
        if (kid.getInfo().getSurname() == null) {
            log.error("This kid dont have surname");
            throw new RuntimeException("This kid dont have surname");
        }
        if (kid.getInfo().getName() == null) {
            log.error("This kid dont have name");
            throw new RuntimeException("This kid dont have name");
        }

        if (kidInfoRepository.findByNameAndSurname(kid.getInfo().getName(),
            kid.getInfo().getSurname()).isEmpty()) {
            kid.setInfo(saveKidInfo(kid.getInfo()));
        } else {
            throw new RuntimeException("Can not create a kid this name and surname already used");
        }

        if (kid.getClothes() == null)
            kid.setClothes(new KidClothes());

        kid.setClothes(saveKidClothes(kid.getClothes()));


        return kidRepository.save(kid);
    }

    private KidClothes saveKidClothes(KidClothes clothes) {
        var skeleton = kidClothesRepository.save(KidClothes.builder().build());

        addStuffToKidClothes(clothes.getHelmet(), skeleton);
        addStuffToKidClothes(clothes.getJacket(), skeleton);
        addStuffToKidClothes(clothes.getGloves(), skeleton);
        addStuffToKidClothes(clothes.getPolo(), skeleton);
        addStuffToKidClothes(clothes.getHat(), skeleton);
        addStuffToKidClothes(clothes.getParka(), skeleton);
        addStuffToKidClothes(clothes.getCap(), skeleton);
        addStuffToKidClothes(clothes.getFirefightingBoots(), skeleton);
        addStuffToKidClothes(clothes.getPullover(), skeleton);
        addStuffToKidClothes(clothes.getTrousers(), skeleton);
        addStuffToKidClothes(clothes.getTShirt(), skeleton);
        addStuffToKidClothes(clothes.getSoftShellJacket(), skeleton);

        return kidClothesRepository.save(skeleton);
    }

    private void addStuffToKidClothes(Stuff stuff, KidClothes clothes) {
        if (stuff == null)
            return;
        if (clothes.getStuff(stuff.getStuffType()) != null) {
            log.warn("Can not add: This kid already have " + stuff.getStuffType().name());
            throw new RuntimeException("Can not add: This kid already have " + stuff.getStuffType().name());
        }
        var _stuff = saveStuffToClothes(stuff, clothes.getId());
        clothes.setStuff(_stuff);
    }

    private Stuff saveStuffToClothes(Stuff stuff, Integer id) {
        if (stuff == null)
            return null;

        var stuffOptional = stuffRepository.findByBatchCode(stuff.getBatchCode());
        if (stuffOptional.isEmpty()) {
            stuff.setClothesId(id);
            return saveStuff(stuff);
        } else {
            var _stuff = stuffOptional.get();
            if (_stuff.getClothesId() != null) {
                log.error("This " + stuff.getStuffType().name() + " already used");
                throw new RuntimeException("This " + stuff.getStuffType().name() + " already used");
            }
            _stuff.setClothesId(id);
            return stuffRepository.save(_stuff);

        }
    }

    private KidInfo saveKidInfo(KidInfo info) {
        return kidInfoRepository.save(info);
    }

    public void addStuffToKid(Integer stuffId, Integer kidId) {
        var stuff = stuffRepository.findById(stuffId);
        var kid = kidRepository.findById(kidId);
        if (stuff.isPresent() && kid.isPresent()) {
            addStuffToKid(stuff.get(), kid.get());
            return;
        }
        throw new RuntimeException("Something went wrong");
    }


    void addStuffToKid(Stuff stuff, Kid kid) {
        if (kid == null) {
            log.warn("Can not add stuff to kid : kid is null");
            throw new RuntimeException("Can not add stuff to kid : kid is null");
        }
        if (kid.getClothes() == null) {
            log.warn("Can not add stuff to kid : kids clothes is null");
            throw new RuntimeException("Can not add stuff to kid : kids clothes is null");
        }

        addStuffToKidClothes(stuff, kid.getClothes());
        kidClothesRepository.save(kid.getClothes());
    }

    public void fetchKidInfo(KidInfo kidInfo) {
        if (kidInfo == null)
            return;
        if (kidInfoRepository.findById(kidInfo.getId()).isEmpty())
            throw new RuntimeException("Something went wrong");
        kidInfoRepository.save(kidInfo);
    }
}
