package com.habitservice.habit_service.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommonUtil {
    private final ObjectMapper objectMapper;
    public String getToken(String auth) {
//        String token = null;
        if (auth != null && auth.startsWith("Bearer ")) {
            return auth.substring(7); // remove "Bearer " prefix
        } else {
            return null;
        }
    }

    public String getUserIdFromToken(String token) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length != 3) throw new RuntimeException("Invalid JWT token");

            String payload = new String(Base64.getUrlDecoder().decode(parts[1]));

            Map<String, Object> tokenJson = objectMapper.readValue(payload, Map.class);
            return (String) tokenJson.get("email");
        } catch (Exception e) {
            throw new RuntimeException("Failed to decode JWT", e);
        }
    }
}
