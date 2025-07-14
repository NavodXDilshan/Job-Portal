package com.dsproject.joblisting_service.Config;

import com.dsproject.joblisting_service.Client.ApplicationClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    @Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;

    @Bean
    public WebClient applicationWebClient() {
        return WebClient.builder()
                .baseUrl("http://application-service")
                .filter(filterFunction)
                .build();
    }

    @Bean
    public ApplicationClient applicationClient() {
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(applicationWebClient())) // Updated method
                .build();

        return httpServiceProxyFactory.createClient(ApplicationClient.class);
    }
}