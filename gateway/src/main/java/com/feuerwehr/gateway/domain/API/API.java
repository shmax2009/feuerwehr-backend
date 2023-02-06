package com.feuerwehr.gateway.domain.API;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class API {
    private String version;

    private List<Route> routes;

}
