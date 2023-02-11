package com.feuerwehr.kleiderkammer.domain.models.dto.adult;

import com.feuerwehr.kleiderkammer.domain.models.adult.Adult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdultDTO {
    private Integer id;

    private AdultInfoDTO info;

    private AdultClothesDTO clothes;

    public AdultDTO(Adult adult) {
        if (adult == null)
            return;
        id = adult.getId();
        info = new AdultInfoDTO(adult.getInfo());
        clothes = new AdultClothesDTO(adult.getClothes());
    }

    public Adult toAdult() {
        return Adult.builder()
            .id(id)
            .clothes(clothes.toAdultClothes())
            .info(info.toAdultInfo())
            .build();
    }
}
