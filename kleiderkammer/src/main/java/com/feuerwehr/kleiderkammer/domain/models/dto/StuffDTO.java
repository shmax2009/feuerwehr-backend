package com.feuerwehr.kleiderkammer.domain.models.dto;

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

    //    private Map<String, Object> parameters;
    private Integer clothesId;

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
        if (stuff.getParameters() != null) {
            parameters = new ArrayList<>();
            var paramMap = new JSONObject(stuff.getParameters()).toMap();
            for (var param : paramMap.keySet()) {
                var localParamMap = (HashMap) paramMap.get(param);
                parameters.add(Parameter.fromMap(localParamMap));
            }
        }
    }


    public Stuff toStuff() {
        var stuff = new Stuff(id, model, size, batchCode, date, additionalInfo, stuffType, null, clothesId);
        stuff.setParameters(parameters);
        return stuff;
    }

}
