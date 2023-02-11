package com.feuerwehr.kleiderkammer.domain.models.dto.adult;

import com.feuerwehr.kleiderkammer.domain.enums.Achivement;
import com.feuerwehr.kleiderkammer.domain.models.adult.AdultInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdultInfoDTO {
    private Integer id;
    private String name;

    private String surname;
    private Long age;
    private Achivement achivement;

    public AdultInfoDTO(AdultInfo adultInfo) {
        id = adultInfo.getId();
        name = adultInfo.getName();
        surname = adultInfo.getSurname();
        age = adultInfo.getAge();
        achivement = adultInfo.getAchivement();
    }

    public AdultInfo toAdultInfo() {
        return AdultInfo.builder()
            .age(age)
            .name(name)
            .achivement(achivement)
            .surname(surname)
            .id(id)
            .build();
    }
}
