package com.devsu.bank.ms.accounts.config;

import com.devsu.bank.ms.accounts.config.properties.ClientServiceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
public class GeneralConfig {

    @Bean
    RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Arrays.asList(
                new BasicAuthenticationInterceptor("user", "pass")
        ));
        return restTemplate;
    }

    @Bean
    @ConfigurationProperties(prefix = "com.devsu.services.clients")
    ClientServiceProperties clientServiceProperties() {
        return new ClientServiceProperties();
    }

}
