package com.feuerwehr.kleiderkammer.domain.models;

import com.feuerwehr.kleiderkammer.domain.enums.Achivement;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Entity
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AdultInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    private String surname;
    private Long age;
    private Achivement achivement;

    public AdultInfo(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public AdultInfo(String name, String surname, Long age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public AdultInfo(String name, String surname, Long age, Achivement _achivement) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        achivement = _achivement;
    }
}


