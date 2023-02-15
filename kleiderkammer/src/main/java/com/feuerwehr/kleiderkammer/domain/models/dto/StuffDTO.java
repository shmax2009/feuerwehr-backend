package com.feuerwehr.kleiderkammer.domain.models.dto;

import com.feuerwehr.kleiderkammer.domain.enums.PersonType;
import com.feuerwehr.kleiderkammer.domain.enums.StuffType;
import com.feuerwehr.kleiderkammer.domain.models.clothes.Parameter;
import com.feuerwehr.kleiderkammer.domain.models.clothes.Stuff;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StuffDTO {

    private Integer id;

    private String model;

    private Integer size;

    private Integer batchCode;

    private Date date;

    private String additionalInfo;

    private StuffType stuffType;

    private List<Parameter> parameters;

    private Integer clothesId;


    private PersonType personType;

    public StuffDTO(Stuff stuff) {
        if (stuff == null)
            return;
        id = stuff.getId();
        model = stuff.getModel();
        size = stuff.getSize();
        batchCode = stuff.getBatchCode();
        date = stuff.getDate();
        additionalInfo = stuff.getAdditionalInfo();
        stuffType = stuff.getStuffType();
        clothesId = stuff.getClothesId();
        personType = stuff.getPersonType();
        if (stuff.getParameters() != null) {
            parameters = new ArrayList<>();
            var paramMap = new JSONObject(stuff.getParameters()).toMap();
            for (var param : paramMap.values()) {

                parameters.add(Parameter.fromMap((HashMap<String, String>) param));
            }
        }
    }

    public static StuffDTO createStuff(Stuff stuff) {
        if (stuff == null)
            return null;

        return new StuffDTO(stuff);
    }


    public Stuff toStuff() {
        var stuff = new Stuff(id, model, size, batchCode, date, additionalInfo, stuffType, null, clothesId, personType);
        stuff.setParameters(parameters);
        return stuff;
    }

}
