package org.habittracker.service;

import lombok.RequiredArgsConstructor;
import org.habittracker.dto.request.UserRequest;
import org.habittracker.dto.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {
    private final WebClient.Builder webClientBuilder;

    public Mono<UserResponse> getSyncUser(UserRequest userRequest) {
        return webClientBuilder.build().post()
                .uri("http://User-Service/api/user/signup")
                .bodyValue(userRequest)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .onErrorResume(WebClientResponseException.class, e -> Mono.error(new RuntimeException(e.getMessage())));
    }
}
