package org.habittracker.Config;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.habittracker.dto.request.UserRequest;
import org.habittracker.dto.response.UserResponse;
import org.habittracker.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class KeyClockUserSyncRequest implements WebFilter {
    private final UserService userService;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (StringUtils.isNotBlank(token)) {
            UserRequest userRequest = getUserRequestfromToken(token);
            if (userRequest != null) {
                return userService.getSyncUser(userRequest).then(chain.filter(exchange));
            }
            return chain.filter(exchange);
        }
        return chain.filter(exchange);
    }
    private UserRequest getUserRequestfromToken (String token){
        try {
            String tokenWithoutBearer = token.replace("Bearer ", "");
            SignedJWT signedJWT = SignedJWT.parse(tokenWithoutBearer);
            JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();

            return UserRequest.builder()
                    .keyClockId(claimsSet.getStringClaim("sub"))
                    .name(claimsSet.getStringClaim("name"))
                    .emailId(claimsSet.getStringClaim("email"))
                    .build();
        } catch (Exception e) {
            return null;
        }
    }
}
