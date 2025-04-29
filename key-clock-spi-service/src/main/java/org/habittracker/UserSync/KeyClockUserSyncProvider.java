package org.habittracker.UserSync;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.habittracker.dto.request.UserRequest;
import org.habittracker.dto.response.UserResponse;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AdminEvent;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class KeyClockUserSyncProvider implements EventListenerProvider {
    private final String realmId = "091abee6-b65f-41be-b753-f9b27c66573b";
    private List<EventType> eventType = List.of(EventType.REGISTER, EventType.UPDATE_PROFILE, EventType.UPDATE_EMAIL);

    public KeyClockUserSyncProvider() {
    }

    @Override
    public void onEvent(Event event) {
        System.out.println("Event Started..." + event.getId());
        if (realmId.equals(event.getRealmId()) && eventType.contains(event.getType())) {
            UserRequest userRequest = UserRequest.builder()
                    .keyClockId(event.getUserId())
                    .emailId(event.getDetails().get("email"))
                    .name("test")
                    .build();
            ObjectMapper objectMapper = new ObjectMapper();
//            JSONObject jsonObject = new JSONObject(userRequest.toString());
            try {
                String body = objectMapper.writeValueAsString(userRequest);
                System.out.println(body);
                HttpClient client = HttpClient.newHttpClient();

//                String body = "{\"username\": \"" + event.getUserId() + "\"}";

                System.out.println("Client is Created.........");
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://host.docker.internal:8083/api/user/signup"))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(body))
                        .build();

                System.out.println("Request is under processs......");

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                System.out.println("Response: " + response.statusCode() + " - " + response.body());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {

    }

    @Override
    public void close() {

    }
}
