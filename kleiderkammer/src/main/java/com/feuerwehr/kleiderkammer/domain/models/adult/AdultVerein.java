package com.feuerwehr.kleiderkammer.domain.models.adult;


import com.feuerwehr.kleiderkammer.domain.ApplyToDatabaseException;
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
@Table(name = "vereins")
public class AdultVerein {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stuff knife;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stuff chainForKnives;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stuff hat;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stuff cap;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stuff softshellJacket;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stuff poloshirt;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stuff tShirt;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stuff pullover;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stuff dailyTrousers;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stuff beltTDPants;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stuff sakko;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stuff shirt;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stuff tie;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stuff peakedCap;


    public void setStuff(Stuff stuff) {
        if (stuff == null)
            return;
        if (stuff.getStuffType() == null)
            return;

        setStuff(stuff, stuff.getStuffType());
    }

    public void setStuff(Stuff stuff, StuffType type) {
        switch (type) {
            case Vereinsmesser -> knife = stuff;

            case KetteFürMesser -> chainForKnives = stuff;

            case Mütze -> hat = stuff;

            case Cap -> cap = stuff;

            case Softshelljacke -> softshellJacket = stuff;

            case Poloshirt -> poloshirt = stuff;

            case TShirt -> tShirt = stuff;

            case Pullover -> pullover = stuff;

            case Tagdiensthose -> dailyTrousers = stuff;

            case GürtelTDHose -> beltTDPants = stuff;

            case Sakko -> sakko = stuff;

            case Hemd -> shirt = stuff;

            case Krawatte -> tie = stuff;

            case Schirmmütze -> peakedCap = stuff;

            default -> throw new ApplyToDatabaseException("Can not set: This type not for adult verein");
        }
    }

    public Stuff getStuff(StuffType type) {
        switch (type) {
            case Vereinsmesser -> {
                return knife;
            }
            case KetteFürMesser -> {
                return chainForKnives;
            }
            case Mütze -> {
                return hat;
            }
            case Cap -> {
                return cap;
            }
            case Softshelljacke -> {
                return softshellJacket;
            }
            case Poloshirt -> {
                return poloshirt;
            }
            case TShirt -> {
                return tShirt;
            }
            case Pullover -> {
                return pullover;
            }
            case Tagdiensthose -> {
                return dailyTrousers;
            }
            case GürtelTDHose -> {
                return beltTDPants;
            }
            case Sakko -> {
                return sakko;
            }
            case Hemd -> {
                return shirt;
            }
            case Krawatte -> {
                return tie;
            }
            case Schirmmütze -> {
                return peakedCap;
            }
            default -> throw new ApplyToDatabaseException("Can not get: This type not for adult vetrein");

        }
    }

}
