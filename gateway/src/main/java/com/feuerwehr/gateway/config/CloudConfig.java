package com.feuerwehr.gateway.config;

import com.feuerwehr.gateway.domain.API.APIRouter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@Slf4j
@AllArgsConstructor
public class CloudConfig {
    private final DiscoveryClient discoveryClient;

    private final APIRouter APIRouter;


    private URI getURI(String service) throws URISyntaxException {
//        log.info("service url - {}", discoveryClient.getInstances(service).get(0).getUri());
           return discoveryClient.getInstances(service).get(0).getUri();
//        return new URI("lb://" + service + '/');
    }


    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        var builderRoutes = builder.routes();
        for (var api : APIRouter.getApis()) {
            for (var path : api.getRoutes()) {
                builderRoutes.route(r ->
                {
//                    r = r.metadata("allowedOrigins", "*");

                    try {
                        return r
                            .path("/api" + "/" + api.getVersion() + "/" + path.getUrl() + "/**")
                            .filters(f -> f.stripPrefix(3))
                            .uri(getURI(path.getServiceName()))
                            ;
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }

        return builderRoutes.build();
    }
}