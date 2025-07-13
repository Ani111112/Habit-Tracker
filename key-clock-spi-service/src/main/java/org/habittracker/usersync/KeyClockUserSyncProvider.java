package org.habittracker.usersync;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.habittracker.dto.request.UserRequest;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class KeyClockUserSyncProvider implements EventListenerProvider {
    private final String realmId = "091abee6-b65f-41be-b753-f9b27c66573b";
    private List<EventType> eventType = List.of(EventType.REGISTER, EventType.UPDATE_PROFILE, EventType.UPDATE_EMAIL);
    private final KeycloakSession session;
    public KeyClockUserSyncProvider(KeycloakSession session) {
        this.session = session;
    }

    @Override
    public void onEvent(Event event) {
        System.out.println("Event Started..." + event.getId());
        if (realmId.equals(event.getRealmId()) && eventType.contains(event.getType())) {
            String keycloakId = event.getUserId();
            String realmId = event.getRealmId();
            RealmModel realmModel = session.realms().getRealm(realmId);

            UserModel userModel = session.users().getUserById(realmModel, keycloakId);

            UserRequest userRequest = UserRequest.builder()
                    .keyClockId(event.getUserId())
                    .emailId(userModel.getEmail())
                    .name(userModel.getFirstName().toUpperCase().concat(" ").concat(userModel.getLastName().toUpperCase()))
                    .build();
            ObjectMapper objectMapper = new ObjectMapper();
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
