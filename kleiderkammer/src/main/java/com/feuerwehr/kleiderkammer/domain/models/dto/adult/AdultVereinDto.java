package com.feuerwehr.kleiderkammer.domain.models.dto.adult;

import com.feuerwehr.kleiderkammer.domain.models.adult.AdultVerein;
import com.feuerwehr.kleiderkammer.domain.models.dto.StuffDTO;
import lombok.Data;

/**
 * A DTO for the {@link AdultVerein} entity
 */
@Data
public class AdultVereinDto {

    private Integer id;
    private StuffDTO knife;
    private StuffDTO chainForKnives;
    private StuffDTO hat;
    private StuffDTO cap;
    private StuffDTO softshellJacket;
    private StuffDTO poloshirt;
    private StuffDTO tShirt;
    private StuffDTO pullover;
    private StuffDTO dailyTrousers;
    private StuffDTO beltTDPants;
    private StuffDTO sakko;
    private StuffDTO shirt;
    private StuffDTO tie;
    private StuffDTO peakedCap;


    public AdultVereinDto(AdultVerein adultVerein) {
        id = adultVerein.getId();
        knife = StuffDTO.createStuff(adultVerein.getKnife());
        chainForKnives = StuffDTO.createStuff(adultVerein.getChainForKnives());
        hat = StuffDTO.createStuff(adultVerein.getHat());
        cap = StuffDTO.createStuff(adultVerein.getCap());
        softshellJacket = StuffDTO.createStuff(adultVerein.getSoftshellJacket());
        poloshirt = StuffDTO.createStuff(adultVerein.getPoloshirt());
        tShirt = StuffDTO.createStuff(adultVerein.getTShirt());
        pullover = StuffDTO.createStuff(adultVerein.getPullover());
        dailyTrousers = StuffDTO.createStuff(adultVerein.getDailyTrousers());
        beltTDPants = StuffDTO.createStuff(adultVerein.getBeltTDPants());
        sakko = StuffDTO.createStuff(adultVerein.getSakko());
        shirt = StuffDTO.createStuff(adultVerein.getShirt());
        tie = StuffDTO.createStuff(adultVerein.getTie());
        peakedCap = StuffDTO.createStuff(adultVerein.getPeakedCap());
    }

    public AdultVerein toAdultVerein() {
        return AdultVerein.builder()
            .id(id)
            .beltTDPants(beltTDPants.toStuff())
            .cap(cap.toStuff())
            .chainForKnives(chainForKnives.toStuff())
            .dailyTrousers(dailyTrousers.toStuff())
            .hat(hat.toStuff())
            .knife(knife.toStuff())
            .peakedCap(peakedCap.toStuff())
            .poloshirt(poloshirt.toStuff())
            .pullover(pullover.toStuff())
            .sakko(sakko.toStuff())
            .shirt(shirt.toStuff())
            .softshellJacket(softshellJacket.toStuff())
            .tie(tie.toStuff())
            .tShirt(tShirt.toStuff())
            .build();
    }

}