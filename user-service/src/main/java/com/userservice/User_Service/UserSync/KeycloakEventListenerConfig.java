//package com.userservice.User_Service.UserSync;
//
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.reactive.function.client.WebClient;
//
//@Configuration
//public class KeycloakEventListenerConfig {
//    @Autowired
//    private WebClient.Builder webClientBuilder;
//
//    @PostConstruct
//    public void init() {
//        KeyclockExternalDbSyncProviderFactory.setWebClientBuilder(webClientBuilder);
//    }
//}
