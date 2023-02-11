package com.feuerwehr.kleiderkammer.domain.models.clothes.builders;

import com.feuerwehr.kleiderkammer.domain.models.clothes.Parameter;
import com.feuerwehr.kleiderkammer.domain.models.clothes.Stuff;
import com.feuerwehr.kleiderkammer.domain.enums.StuffType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class StuffBuilder {
    private Integer id;
    private String model;
    private Integer size;
    private Integer batchCode;
    private Date date;
    private String additionalInfo;
    private StuffType stuffType;
    private List<Parameter> parameters;
    private Integer clothesId;

    public StuffBuilder() {
    }

    public StuffBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public StuffBuilder model(String model) {
        this.model = model;
        return this;
    }

    public StuffBuilder size(Integer size) {
        this.size = size;
        return this;
    }

    public StuffBuilder batchCode(Integer batchCode) {
        this.batchCode = batchCode;
        return this;
    }

    public StuffBuilder date(Date date) {
        this.date = date;
        return this;
    }

    public StuffBuilder additionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
        return this;
    }

    public StuffBuilder type(StuffType stuffType) {
        this.stuffType = stuffType;
        return this;
    }

    public StuffBuilder parameters(List<Parameter> parameters) {
        this.parameters = parameters;
        return this;
    }

    public StuffBuilder parameter(Parameter parameter) {
        if (this.parameters == null) {
            this.parameters = new ArrayList<>();
        }
        this.parameters.add(parameter);
        return this;
    }

    public StuffBuilder clothesId(Integer clothesId) {
        this.clothesId = clothesId;
        return this;
    }

    public Stuff build() {
        Stuff stuff = new Stuff();
        stuff.setId(id);
        stuff.setModel(model);
        stuff.setSize(size);
        stuff.setBatchCode(batchCode);
        stuff.setDate(date);
        stuff.setAdditionalInfo(additionalInfo);
        stuff.setStuffType(stuffType);
        stuff.setParameters(parameters);
        stuff.setClothesId(clothesId);
        return stuff;
    }
}
