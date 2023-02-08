package com.feuerwehr.kleiderkammer.domain.models.clothes;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parameter {
    private String type;
    private String name;
    private String value;

    public <T> T parse(Class<T> tClass) {
        if (!tClass.getSimpleName().equals(type)) {
            return null;
        }
        return Parser.parse(tClass, value);
    }

    public static Parameter fromMap(HashMap<String, String> map) {
        return new Parameter(map.get("type"), map.get("name"),
            map.get("value"));
    }

}

// Integer i = h.parse<Integer>(Integer.class)
