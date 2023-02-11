package com.feuerwehr.kleiderkammer.domain.models.dto.kid;

import com.feuerwehr.kleiderkammer.domain.models.adult.Adult;
import com.feuerwehr.kleiderkammer.domain.models.kid.Kid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KidDTO {
    private Integer id;

    private KidInfoDTO info;

    private KidClothesDTO clothes;

    public KidDTO(Kid kid) {
        id = kid.getId();
        info = new KidInfoDTO(kid.getInfo());
        clothes = new KidClothesDTO(kid.getClothes());
    }

    public Kid toKid() {
        return Kid.builder()
            .id(id)
            .info(info.toKidInfo())
            .clothes(clothes.toKidClothes())
            .build();
    }
}
