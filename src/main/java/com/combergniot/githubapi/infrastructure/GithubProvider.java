package com.combergniot.githubapi.infrastructure;

import com.combergniot.githubapi.user.dto.UserProfileSnapshot;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class GithubProvider {

    private static final String GITHUB_URL = "https://api.github.com";

    private final WebClient webClient;

    GithubProvider(WebClient webClient) {
        this.webClient = webClient;
    }

    public UserProfileSnapshot getUserInfo(String login) {
        return webClient.get()
                .uri(GITHUB_URL + "/users/{login}", login)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals,
                        clientResponse -> Mono.empty())
                .bodyToMono(UserProfileSnapshot.class)
                .block(CoreConfiguration.TIMEOUT_OF_BLOCK);
    }
}
