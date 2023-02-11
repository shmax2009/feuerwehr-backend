package com.feuerwehr.kleiderkammer.services;


import com.feuerwehr.kleiderkammer.domain.enums.StuffType;
import com.feuerwehr.kleiderkammer.domain.models.clothes.Stuff;
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

    private void deleteStuff(Stuff stuff) {
        if (stuff == null) return;
        unpairStuff(stuff);
        stuffRepository.delete(stuff);
    }

    public void deleteStuff(Integer id) {
        var stuff = stuffRepository.findById(id);
        if (stuff.isEmpty()) {
            throw new RuntimeException("Can not delete stuff: Stuff with this id dont exist");
        }
        deleteStuff(stuff.get());
    }


    private Boolean unpairStuffAdult(Stuff stuff) {
        if (stuff == null)
            return false;
        var id = stuff.getClothesId();

        var adultClothesOptional = adultClothesRepository.findById(id);
        if (adultClothesOptional.isEmpty())
            return false;

        var adultClothes = adultClothesOptional.get();


        switch (stuff.getStuffType()) {

            case Helm -> adultClothes.setHelmet(null);

            case Einsatzjacke -> adultClothes.setCombatJacket(null);

            case Einsatzhose -> adultClothes.setCombatTrousers(null);

            case Überhose -> adultClothes.setTopTrousers(null);

            case Feuerwehrstiefel -> adultClothes.setFirefightingBoots(null);

            case Gurt -> adultClothes.setBelt(null);

            case HandschuheBrandbekämpfung -> adultClothes.setFirefightingGloves(null);

            case Handschuhe -> adultClothes.setGloves(null);

        }
        stuff.setClothesId(null);
        stuffRepository.save(stuff);
        adultClothesRepository.save(adultClothes);
        return true;
    }

    private Boolean unpairStuffKid(Stuff stuff) {
        if (stuff == null)
            return false;
        var id = stuff.getClothesId();

        var kidClothesOptional = kidClothesRepository.findById(id);
        if (kidClothesOptional.isEmpty())
            return false;

        var kidClothes = kidClothesOptional.get();

        kidClothes.setStuff(null, stuff.getStuffType());

        stuff.setClothesId(null);
        stuffRepository.save(stuff);
        kidClothesRepository.save(kidClothes);
        return true;
    }


    private void unpairStuff(Stuff stuff) {
        if (stuff == null) {
            return;
        }
        if (unpairStuffAdult(stuff))
            return;
        unpairStuffKid(stuff);
    }

    public void unpair(Integer id) {
        var stuff = stuffRepository.findById(id);
        if (stuff.isEmpty())
            throw new RuntimeException("Can not unpair stuff: stuff dont exist");

        unpairStuff(stuff.get());
    }


    public void deleteAdult(Integer id) {
        var adultOptional = adultRepository.findById(id);

        if (adultOptional.isEmpty())
            throw new RuntimeException("Can not delete adult: this adult dont exist");

        var adult = adultOptional.get();

        deleteAdultClothes(adult.getClothes().getId());
//        adult.setAdultClothes(null);
        deleteAdultInfo(adult.getInfo().getId());
//        adult.setAdultInfo(null);

        adultRepository.delete(adult);

    }


    private void deleteAdultInfo(Integer id) {
        if (!adultInfoRepository.existsById(id))
            throw new RuntimeException("Can not delete adult: this adult dont have a adultInfo");
        adultInfoRepository.deleteById(id);
    }


    private void deleteAdultClothes(Integer id) {

        var adultClothesOptional = adultClothesRepository.findById(id);

        if (adultClothesOptional.isEmpty())
            throw new RuntimeException("Can not delete adult: this adult dont have a adultInfo");

        var adultClothes = adultClothesOptional.get();
        unpairStuff(adultClothes.getGloves());
        unpairStuff(adultClothes.getHelmet());
        unpairStuff(adultClothes.getCombatJacket());
        unpairStuff(adultClothes.getBelt());
        unpairStuff(adultClothes.getFirefightingBoots());
        unpairStuff(adultClothes.getFirefightingGloves());
        unpairStuff(adultClothes.getTopTrousers());
        unpairStuff(adultClothes.getCombatTrousers());


//        adultClothes.setGloves(null);
//        adultClothes.setHelmet(null);
//        adultClothes.setBelt(null);
//        adultClothes.setCombatTrousers(null);
//        adultClothes.setTopTrousers(null);
//        adultClothes.setFirefightingBoots(null);
//        adultClothes.setFirefightingGloves(null);
//        adultClothes.setCombatJacket(null);

        adultClothesRepository.deleteById(id);
    }

    public void deleteKid(Integer id) {
        var kidOptional = kidRepository.findById(id);

        if (kidOptional.isEmpty())
            throw new RuntimeException("Can not delete kid: this kid dont exist");

        var kid = kidOptional.get();

        deleteKidClothes(kid.getClothes().getId());
//        adult.setAdultClothes(null);
        deleteKidInfo(kid.getInfo().getId());
//        adult.setAdultInfo(null);

        kidRepository.delete(kid);
    }

    private void deleteKidInfo(Integer id) {
        if (!kidInfoRepository.existsById(id))
            throw new RuntimeException("Can not delete kid: this kid dont have a kidInfo");
        kidInfoRepository.deleteById(id);
    }

    private void deleteKidClothes(Integer id) {
        var kidClothesOptional = adultClothesRepository.findById(id);

        if (kidClothesOptional.isEmpty())
            throw new RuntimeException("Can not delete kid: this kid dont have a kidClothes");

        var kidClothes = kidClothesOptional.get();

        unpairStuffKid(kidClothes.getStuff(StuffType.JugendHelm));
        unpairStuffKid(kidClothes.getStuff(StuffType.JugendJackeSommer));
        unpairStuffKid(kidClothes.getStuff(StuffType.JugendHandschuhe));
        unpairStuffKid(kidClothes.getStuff(StuffType.Cap));
        unpairStuffKid(kidClothes.getStuff(StuffType.JugendMütze));
        unpairStuffKid(kidClothes.getStuff(StuffType.JugendParkaWinter));
        unpairStuffKid(kidClothes.getStuff(StuffType.JugendPolo));
        unpairStuffKid(kidClothes.getStuff(StuffType.Pullover));
        unpairStuffKid(kidClothes.getStuff(StuffType.SoftshellJacke));
        unpairStuffKid(kidClothes.getStuff(StuffType.JugendHose));
        unpairStuffKid(kidClothes.getStuff(StuffType.TShirt));
        unpairStuffKid(kidClothes.getStuff(StuffType.Feuerwehrstiefel));


        kidClothesRepository.deleteById(id);
    }
}
