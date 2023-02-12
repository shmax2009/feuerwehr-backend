package com.feuerwehr.kleiderkammer.domain.models.adult;

import com.feuerwehr.kleiderkammer.domain.enums.StuffType;
import com.feuerwehr.kleiderkammer.domain.models.clothes.Stuff;
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


    public void setStuff(Stuff stuff) {
        setStuff(stuff, stuff.getStuffType());
    }


    public void setStuff(Stuff stuff, StuffType type) {
        switch (type) {
            case Helm -> {
                setHelmet(stuff);
            }
            case Einsatzjacke -> {
                setCombatJacket(stuff);
            }
            case Einsatzhose -> {
                setCombatTrousers(stuff);
            }
            case Überhose -> {
                setTopTrousers(stuff);
            }
            case Feuerwehrstiefel -> {
                setFirefightingBoots(stuff);
            }
            case Gurt -> {
                setBelt(stuff);
            }
            case HandschuheBrandbekämpfung -> {

                setFirefightingGloves(stuff);
            }
            case Handschuhe -> {
                setGloves(stuff);
            }
            default -> {
                throw new RuntimeException("Can not set: This type not for adult");
            }
        }
    }


    public Stuff getStuff(StuffType type) {
        switch (type) {
            case Helm -> {
                return helmet;
            }
            case Einsatzjacke -> {
                return combatJacket;
            }
            case Einsatzhose -> {
                return combatTrousers;
            }
            case Überhose -> {
                return topTrousers;
            }
            case Feuerwehrstiefel -> {
                return firefightingBoots;
            }
            case Gurt -> {
                return belt;
            }
            case HandschuheBrandbekämpfung -> {
                return firefightingGloves;
            }
            case Handschuhe -> {
                return gloves;
            }
            default -> {
                throw new RuntimeException("Can not set: This type not for adult");
            }
        }
    }

}
