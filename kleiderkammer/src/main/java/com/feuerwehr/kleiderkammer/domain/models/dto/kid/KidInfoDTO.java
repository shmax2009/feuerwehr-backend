package com.feuerwehr.kleiderkammer.domain.models.dto.kid;

import com.feuerwehr.kleiderkammer.domain.enums.Achivement;
import com.feuerwehr.kleiderkammer.domain.models.kid.KidInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KidInfoDTO {
    private Integer id;
    private String name;

    private String surname;
    private Long age;
    private Achivement achivement;

    public KidInfoDTO(KidInfo kidInfo) {
        id = kidInfo.getId();
        name = kidInfo.getName();
        surname = kidInfo.getSurname();
        age = kidInfo.getAge();
    }

    public KidInfo toKidInfo() {
        return KidInfo.builder()
            .id(id)
            .age(age)
            .name(name)
            .surname(surname)
            .build();
    }
}
