package com.feuerwehr.kleiderkammer.domain.models.dto;

import com.feuerwehr.kleiderkammer.domain.models.Adult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdultDTO {
    private Integer id;

    private AdultInfoDTO adultInfo;

    private AdultClothesDTO adultClothes;

    public AdultDTO(Adult adult) {
        id = adult.getId();
        adultInfo = new AdultInfoDTO(adult.getAdultInfo());
        adultClothes = new AdultClothesDTO(adult.getAdultClothes());
    }

    public Adult toAdult() {
        return Adult.builder()
            .id(id)
            .adultClothes(adultClothes.toAdultClothes())
            .adultInfo(adultInfo.toAdultInfo())
            .build();
    }
}
