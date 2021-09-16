package com.combergniot.githubapi.infrastructure;

import java.time.Duration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
class CoreConfiguration {

    static final Duration TIMEOUT_OF_BLOCK = Duration.ofSeconds(5);

    @Bean
    WebClient client() {
        return WebClient.create();
    }

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
