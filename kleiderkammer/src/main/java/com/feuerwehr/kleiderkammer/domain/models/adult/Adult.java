package com.feuerwehr.kleiderkammer.domain.models.adult;

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
    private AdultInfo info;

    @OneToOne(fetch = FetchType.EAGER)
    private AdultClothes clothes;


    public Adult(AdultInfo info, AdultClothes clothes) {
        this.info = info;
        this.clothes = clothes;
    }
}
