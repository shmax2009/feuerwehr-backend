package com.feuerwehr.kleiderkammer.domain.models.kid;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.feuerwehr.kleiderkammer.domain.models.adult.AdultClothes;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@ToString
@Table(name = "kids")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"kids", "hibernateLazyInitializer"})
public class Kid {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @OneToOne(fetch = FetchType.EAGER)
    private KidInfo info;

    @OneToOne(fetch = FetchType.EAGER)
    private KidClothes clothes;


    public Kid(KidInfo info, KidClothes clothes) {
        this.info = info;
        this.clothes = clothes;
    }
}
