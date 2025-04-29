//package com.userservice.User_Service.UserSync;
//
//
//import com.userservice.User_Service.Mappers.UserInfoMapper;
//import com.userservice.User_Service.dto.request.UserRequest;
//import com.userservice.User_Service.dto.response.UserResponse;
//import com.userservice.User_Service.model.ApplicationUser;
//import com.userservice.User_Service.repository.UserRepository;
//import lombok.NoArgsConstructor;
//import org.keycloak.events.Event;
//import org.keycloak.events.EventListenerProvider;
//import org.keycloak.events.EventType;
//import org.keycloak.events.admin.AdminEvent;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.client.WebClient;
//
//import java.util.Date;
//import java.util.List;
//
//@Component
//public class KeyClockUserSyncProvider implements EventListenerProvider {
//    private final String realmId = "091abee6-b65f-41be-b753-f9b27c66573b";
//    private List<EventType> eventType = List.of(EventType.REGISTER, EventType.UPDATE_PROFILE, EventType.UPDATE_EMAIL);
//    private final WebClient.Builder webClientBuilder;
//
//    public KeyClockUserSyncProvider(WebClient.Builder webClientBuilder) {
//        this.webClientBuilder = webClientBuilder;
//    }
//
//    @Override
//    public void onEvent(Event event) {
//        System.out.println("Event Started..." + event.getId());
//        if (realmId.equals(event.getRealmId()) && eventType.contains(event.getType())) {
////            ApplicationUser applicationUser = UserInfoMapper.INSTANCE.map(UserRequest.builder()
////                            .keyClockId(event.getUserId())
////                            .emailId(event.getDetails().get("email"))
////                    .build());
//            UserRequest userRequest = UserRequest.builder()
//                    .keyClockId(event.getUserId())
//                    .emailId(event.getDetails().get("email"))
//                    .build();
//
////            applicationUser.setCreatedOn(new Date());
//            webClientBuilder.build().post()
//                    .uri("http://User-Service/api/user/signup")
//                    .bodyValue(userRequest)
//                    .retrieve()
//                    .bodyToMono(UserResponse.class)
//                    .subscribe();
//        }
//    }
//
//    @Override
//    public void onEvent(AdminEvent adminEvent, boolean b) {
//
//    }
//
//    @Override
//    public void close() {
//
//    }
//}
