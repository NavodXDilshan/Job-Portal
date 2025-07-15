package com.dsproject.api_gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("application-service", r -> r
                        .path("/application/**")
                        .uri("lb://application-service"))
                .route("joblisting-service", r -> r
                        .path("/job/**")
                        .uri("lb://joblisting-service"))
                .route("assignment-service", r -> r
                        .path("/assignment/**")
                        .uri("lb://assignment-service"))
                .route("profile-service", r -> r
                        .path("/profile/**")
                        .uri("lb://profile-service"))
                .build();
    }
}