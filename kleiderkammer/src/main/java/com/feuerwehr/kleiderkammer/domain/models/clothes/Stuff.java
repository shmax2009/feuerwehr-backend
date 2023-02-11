package com.feuerwehr.kleiderkammer.domain.models.clothes;


import com.feuerwehr.kleiderkammer.domain.enums.StuffType;
import com.feuerwehr.kleiderkammer.domain.models.clothes.builders.StuffBuilder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    private String parameters;

    private Integer clothesId;

    public Parameter getParameter(String name) {
        if (parameters == null)
            parameters = "{}";
        var obj = new JSONObject(parameters).toMap();
        var header = obj.get(name);
        if (header == null)
            throw new RuntimeException("This parameter is not exist");
        return Parameter.fromMap((HashMap<String, String>) header);

    }

    public void setParameters(List<Parameter> parameters) {
        if (parameters == null)
            return;
        parameters.forEach(this::addParameter);
    }

    public void addParameter(Parameter parameter) {
        if (parameters == null)
            parameters = "{}";
        JSONObject obj = new JSONObject(parameters);
        Map<String, Object> map = obj.toMap();
        map.put(parameter.getName(), parameter);
        parameters = new JSONObject(map).toString();
    }


    public static StuffBuilder builder() {
        return new StuffBuilder();
    }


}
