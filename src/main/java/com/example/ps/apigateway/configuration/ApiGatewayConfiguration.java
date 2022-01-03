package com.example.ps.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder
                .routes()
                .route(p -> p.path("/get")
                        .filters(f -> f.addRequestHeader("MY_HEADER", "MyHeader"))
                        .uri("http://httpbin.org:80"))
                .route(p -> p.path("/currency-exchange/**").uri("lb://currency-exchange"))
                .route(p -> p.path("/currency-conversion/**").uri("lb://currency-conversion"))
                .build();
    }
}
