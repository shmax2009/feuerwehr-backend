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

        helmet = StuffDTO.createStuff(kidClothes.getHelmet());

        jacket = StuffDTO.createStuff(kidClothes.getJacket());

        parka = StuffDTO.createStuff(kidClothes.getParka());

        trousers = StuffDTO.createStuff(kidClothes.getTrousers());

        gloves = StuffDTO.createStuff(kidClothes.getGloves());

        firefightingBoots = StuffDTO.createStuff(kidClothes.getFirefightingBoots());

        hat = StuffDTO.createStuff(kidClothes.getHat());

        polo = new StuffDTO(kidClothes.getPolo());

        tShirt = new StuffDTO(kidClothes.getTShirt());

        pullover = new StuffDTO(kidClothes.getPullover());

        softShellJacket = new StuffDTO(kidClothes.getSoftShellJacket());

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
