package com.feuerwehr.kleiderkammer.domain.models.clothes;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "parameters")
@Entity
public class Parameter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String type;
    private String name;
    private String value;

    public <T> T parse(Class<T> tClass) {
        if (!tClass.getSimpleName().equals(type)) {
            return null;
        }
        return Parser.parse(tClass, value);
    }

}

// Integer i = h.parse<Integer>(Integer.class)
