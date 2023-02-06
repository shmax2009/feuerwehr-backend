package com.feuerwehr.kleiderkammer.domain.models;

import com.feuerwehr.kleiderkammer.domain.models.clothes.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AdultClothes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stuff combatJacket;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stuff combatTrousers;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stuff firefightingBoots;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stuff firefightingGloves;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stuff gloves;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stuff helmet;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stuff topTrousers;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stuff belt;
}
