package com.feuerwehr.kleiderkammer.domain.models.kid;


import com.feuerwehr.kleiderkammer.domain.enums.StuffType;
import com.feuerwehr.kleiderkammer.domain.models.clothes.Stuff;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class KidClothes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Stuff helmet;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Stuff jacket;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Stuff parka;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Stuff trousers;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Stuff gloves;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Stuff firefightingBoots;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Stuff hat;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Stuff polo;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Stuff tShirt;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Stuff pullover;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Stuff softShellJacket;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Stuff cap;


    public void setStuff(Stuff stuff) {
        if (stuff == null)
            return;

        setStuff(stuff, stuff.getStuffType());
    }


    public void setStuff(Stuff stuff, StuffType type) {
        switch (type) {
            case JugendHelm -> {
                setHelmet(stuff);
            }
            case JugendJackeSommer -> {
                setJacket(stuff);
            }
            case JugendHandschuhe -> {
                setGloves(stuff);
            }
            case Cap -> {
                setCap(stuff);
            }
            case Feuerwehrstiefel -> {
                setFirefightingBoots(stuff);
            }
            case JugendMütze -> {
                setHat(stuff);
            }
            case JugendParkaWinter -> {
                setParka(stuff);
            }
            case JugendPolo -> {
                setPolo(stuff);
            }
            case Pullover -> {
                setPullover(stuff);
            }
            case SoftshellJacke -> {
                setSoftShellJacket(stuff);
            }
            case JugendHose -> {
                setTrousers(stuff);
            }
            case TShirt -> {
                setTShirt(stuff);
            }

            default -> {
                throw new RuntimeException("Can not set: This type not for jugend");
            }
        }
    }


    public Stuff getStuff(StuffType type) {
        switch (type) {
            case JugendHelm -> {
                return helmet;
            }
            case JugendJackeSommer -> {
                return jacket;
            }
            case JugendHandschuhe -> {
                return gloves;
            }
            case Cap -> {
                return cap;
            }
            case Feuerwehrstiefel -> {
                return firefightingBoots;
            }
            case JugendMütze -> {
                return hat;
            }
            case JugendParkaWinter -> {
                return parka;
            }
            case JugendPolo -> {
                return polo;
            }
            case Pullover -> {
                return pullover;
            }
            case SoftshellJacke -> {
                return softShellJacket;
            }
            case JugendHose -> {
                return trousers;
            }
            case TShirt -> {
                return tShirt;
            }
            default -> {
                throw new RuntimeException("Can not set: This type not for jugend");
            }
        }
    }

}
