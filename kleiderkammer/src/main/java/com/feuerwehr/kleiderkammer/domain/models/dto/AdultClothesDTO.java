package com.feuerwehr.kleiderkammer.domain.models.dto;

import com.feuerwehr.kleiderkammer.domain.models.AdultClothes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
        if (adultClothes.getCombatJacket() != null)
            combatJacket = new StuffDTO(adultClothes.getCombatJacket());
        if (adultClothes.getCombatTrousers() != null)
            combatTrousers = new StuffDTO(adultClothes.getCombatTrousers());
        if (adultClothes.getFirefightingBoots() != null)
            firefightingBoots = new StuffDTO(adultClothes.getFirefightingBoots());
        if (adultClothes.getFirefightingGloves() != null)
            firefightingGloves = new StuffDTO(adultClothes.getFirefightingGloves());
        if (adultClothes.getGloves() != null)
            gloves = new StuffDTO(adultClothes.getGloves());
        if (adultClothes.getHelmet() != null)
            helmet = new StuffDTO(adultClothes.getHelmet());
        if (adultClothes.getTopTrousers() != null)
            topTrousers = new StuffDTO(adultClothes.getTopTrousers());
        if (adultClothes.getBelt() != null)
            belt = new StuffDTO(adultClothes.getBelt());
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
