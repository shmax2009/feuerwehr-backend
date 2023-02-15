package com.feuerwehr.kleiderkammer.domain.models.dto.adult;

import com.feuerwehr.kleiderkammer.domain.models.adult.Adult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * A DTO for the {@link AdultDTO} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdultDTO {
    private Integer id;

    private AdultInfoDTO info;

    private AdultClothesDTO clothes;

    private AdultVereinDto vereinDto;

    public AdultDTO(Adult adult) {
        if (adult == null)
            return;
        id = adult.getId();
        info = new AdultInfoDTO(adult.getInfo());
        clothes = new AdultClothesDTO(adult.getClothes());
        vereinDto = new AdultVereinDto(adult.getVerein());
    }

    public Adult toAdult() {
        return Adult.builder()
            .id(id)
            .clothes(clothes.toAdultClothes())
            .info(info.toAdultInfo())
            .verein(vereinDto.toAdultVerein())
            .build();
    }
}
