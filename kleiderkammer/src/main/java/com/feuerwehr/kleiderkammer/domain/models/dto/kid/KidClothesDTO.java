package com.feuerwehr.kleiderkammer.domain.models.dto.kid;

import com.feuerwehr.kleiderkammer.domain.models.dto.StuffDTO;
import com.feuerwehr.kleiderkammer.domain.models.kid.KidClothes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KidClothesDTO {
    private Integer id;

    StuffDTO helmet;

    StuffDTO jacket;

    StuffDTO parka;

    StuffDTO trousers;

    StuffDTO gloves;

    StuffDTO firefightingBoots;

    StuffDTO hat;

    StuffDTO polo;

    StuffDTO tShirt;

    StuffDTO pullover;

    StuffDTO softShellJacket;

    StuffDTO cap;

    public KidClothesDTO(KidClothes kidClothes) {
        if (kidClothes == null)
            return;
        id = kidClothes.getId();

        if (kidClothes.getHelmet() != null)
            helmet = new StuffDTO(kidClothes.getHelmet());

        if (kidClothes.getJacket() != null)
            jacket = new StuffDTO(kidClothes.getJacket());

        if (kidClothes.getParka() != null)
            parka = new StuffDTO(kidClothes.getParka());

        if (kidClothes.getTrousers() != null)
            trousers = new StuffDTO(kidClothes.getTrousers());

        if (kidClothes.getGloves() != null)
            gloves = new StuffDTO(kidClothes.getGloves());

        if (kidClothes.getFirefightingBoots() != null)
            firefightingBoots = new StuffDTO(kidClothes.getFirefightingBoots());

        if (kidClothes.getHat() != null)
            hat = new StuffDTO(kidClothes.getHat());

        if (kidClothes.getPolo() != null)
            polo = new StuffDTO(kidClothes.getPolo());

        if (kidClothes.getTShirt() != null)
            tShirt = new StuffDTO(kidClothes.getTShirt());

        if (kidClothes.getPullover() != null)
            pullover = new StuffDTO(kidClothes.getPullover());

        if (kidClothes.getSoftShellJacket() != null)
            softShellJacket = new StuffDTO(kidClothes.getSoftShellJacket());

        if (kidClothes.getCap() != null)
            cap = new StuffDTO(kidClothes.getCap());

    }


    public KidClothes toKidClothes() {
        return KidClothes.builder()
            .id(id)
            .helmet(helmet.toStuff())
            .jacket(jacket.toStuff())
            .parka(parka.toStuff())
            .trousers(trousers.toStuff())
            .gloves(gloves.toStuff())
            .firefightingBoots(firefightingBoots.toStuff())
            .hat(hat.toStuff())
            .polo(polo.toStuff())
            .tShirt(tShirt.toStuff())
            .pullover(pullover.toStuff())
            .softShellJacket(softShellJacket.toStuff())
            .cap(cap.toStuff())
            .build();
    }
}
