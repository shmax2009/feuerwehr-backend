package com.feuerwehr.kleiderkammer.domain.models.kid;

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
public class KidInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String surname;
    private Long age;

    public KidInfo(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public KidInfo(String name, String surname, Long age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }
}
