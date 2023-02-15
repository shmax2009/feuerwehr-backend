package com.feuerwehr.kleiderkammer.domain.models.dto.adult;

import com.feuerwehr.kleiderkammer.domain.models.adult.AdultClothes;
import com.feuerwehr.kleiderkammer.domain.models.dto.StuffDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * A DTO for the {@link AdultClothes} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdultClothesDTO {
    private Integer id;

    private StuffDTO combatJacket;

    private StuffDTO combatTrousers;

    private StuffDTO firefightingBoots;

    private StuffDTO firefightingGloves;

    private StuffDTO gloves;

    private StuffDTO helmet;

    private StuffDTO topTrousers;

    private StuffDTO belt;

    public AdultClothesDTO(AdultClothes adultClothes) {
        if (adultClothes == null)
            return;
        id = adultClothes.getId();

        combatJacket = StuffDTO.createStuff(adultClothes.getCombatJacket());
        combatTrousers = StuffDTO.createStuff(adultClothes.getCombatTrousers());
        firefightingBoots = StuffDTO.createStuff(adultClothes.getFirefightingBoots());
        firefightingGloves = StuffDTO.createStuff(adultClothes.getFirefightingGloves());
        gloves = StuffDTO.createStuff(adultClothes.getGloves());
        helmet = StuffDTO.createStuff(adultClothes.getHelmet());
        topTrousers = StuffDTO.createStuff(adultClothes.getTopTrousers());
        belt = StuffDTO.createStuff(adultClothes.getBelt());
    }


    public AdultClothes toAdultClothes() {
        return AdultClothes.builder()
            .id(id)
            .combatJacket(combatJacket.toStuff())
            .combatTrousers(combatTrousers.toStuff())
            .helmet(helmet.toStuff())
            .belt(belt.toStuff())
            .firefightingBoots(firefightingBoots.toStuff())
            .firefightingGloves(firefightingGloves.toStuff())
            .topTrousers(topTrousers.toStuff())
            .gloves(gloves.toStuff())
            .build();
    }
}
