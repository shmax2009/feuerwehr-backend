package com.feuerwehr.gateway.domain.API;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@ConfigurationProperties(prefix = "spring.api-router")
@Data
public class APIRouter {
    private List<API> apis;

}
