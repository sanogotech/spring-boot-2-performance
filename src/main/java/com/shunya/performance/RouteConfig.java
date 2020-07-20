package com.shunya.performance;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import  com.shunya.performance.GreetingController;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RouteConfig {
	
	//https://www.baeldung.com/spring-webclient-resttemplate
	//WebClient Non-Blocking Client
	// RestTemplate Blocking Client
	/*
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
   // Do any additional configuration here
   return builder.build();
	}*/

    @Bean
    public RouterFunction<?> routerFunction(HelloController testController) {
        return route(GET("/api/perf"), testController::ping);
    }
	
	
	
	
    @Bean
    public RouterFunction<?> newrouterFunction(HelloController helloController){
        return route(GET("/myasync"), helloController::myasync);
    }
}
