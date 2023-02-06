package com.feuerwehr.kleiderkammer.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@Builder
@ToString
@Table(name = "adults")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"adults", "hibernateLazyInitializer"})
public class Adult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER)
    private AdultInfo adultInfo;

    @OneToOne(fetch = FetchType.EAGER)
    private AdultClothes adultClothes;

    public Adult(AdultInfo adultInfo) {
        this.adultInfo = adultInfo;
    }

    public Adult(AdultInfo adultInfo, AdultClothes adultClothes) {
        this.adultInfo = adultInfo;
        this.adultClothes = adultClothes;
    }
}
