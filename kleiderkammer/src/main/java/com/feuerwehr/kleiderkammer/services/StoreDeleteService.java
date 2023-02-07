package com.feuerwehr.kleiderkammer.services;


import com.feuerwehr.kleiderkammer.domain.models.clothes.Stuff;
import com.feuerwehr.kleiderkammer.domain.repository.AdultClothesRepository;
import com.feuerwehr.kleiderkammer.domain.repository.AdultInfoRepository;
import com.feuerwehr.kleiderkammer.domain.repository.AdultRepository;
import com.feuerwehr.kleiderkammer.domain.repository.clothes.ParameterRepository;
import com.feuerwehr.kleiderkammer.domain.repository.clothes.StuffRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class StoreDeleteService {
    private final ParameterRepository parameterRepository;
    private final AdultClothesRepository adultClothesRepository;
    private final AdultRepository adultRepository;
    private final AdultInfoRepository adultInfoRepository;
    private final StuffRepository stuffRepository;

    private void deleteStuff(Stuff stuff) {
        if (stuff == null) return;
        unpairStuff(stuff);
        stuffRepository.delete(stuff);
    }

    public void deleteStuff(Integer batchCode) {
        var stuff = stuffRepository.findByBatchCode(batchCode);
        if (stuff.isEmpty())
            return;
        deleteStuff(stuff.get());
    }


    private void unpairStuff(Stuff stuff) {
        if (stuff == null) return;
        var id = stuff.getAdultClothesId();

        var adultClothesOptional = adultClothesRepository.findById(id);
        if (adultClothesOptional.isEmpty())
            return;

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
        stuff.setAdultClothesId(null);
        stuffRepository.save(stuff);
        adultClothesRepository.save(adultClothes);
    }


    public void unpair(Integer batchCode) {
        var stuff = stuffRepository.findById(batchCode);
        if (stuff.isEmpty())
            return;

        unpairStuff(stuff.get());
    }

}
