package com.feuerwehr.kleiderkammer.domain.models.clothes;


import com.feuerwehr.kleiderkammer.domain.enums.StuffType;
import com.feuerwehr.kleiderkammer.domain.models.clothes.builders.StuffBuilder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Objects;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stuffs")
@Entity
public class Stuff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String model;

    private Integer size;

    private Integer batchCode;

    private Date date;

    private String additionalInfo;

    private StuffType stuffType;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Parameter> parameters;

    private Integer adultClothesId;

    public Parameter getParameter(String name) {
        var _headers =
            parameters.stream().filter(h -> Objects.equals(h.getName(), name)).toList();
        if (_headers.size() != 1)
            return null;

        return _headers.get(0);
    }


    public static StuffBuilder builder() {
        return new StuffBuilder();
    }


}
