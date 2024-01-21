package com.practice.APIGateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/api/user/**")//userService
                        .uri("http://localhost:8081"))
                .route(p -> p
                        .path("/api/student/user/**")//userService
                        .uri("http://localhost:8085"))
                .route(p -> p
                        .path("/api/student/student/**")//studentService
                        .uri("http://localhost:8085"))
                .route(p -> p
                        .path("/api/student/subject/**")//subjectService
                        .uri("http://localhost:8085"))
                .route(p -> p
                        .path("/api/student/department/**")//departmentService
                        .uri("http://localhost:8085"))
                .build();
    }
}
