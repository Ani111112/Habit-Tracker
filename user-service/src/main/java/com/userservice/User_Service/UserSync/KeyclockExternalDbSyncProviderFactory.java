//package com.userservice.User_Service.UserSync;
//
//import org.keycloak.Config;
//import org.keycloak.events.EventListenerProvider;
//import org.keycloak.events.EventListenerProviderFactory;
//import org.keycloak.models.KeycloakSession;
//import org.keycloak.models.KeycloakSessionFactory;
//import org.springframework.web.reactive.function.client.WebClient;
//
//public class KeyclockExternalDbSyncProviderFactory implements EventListenerProviderFactory {
//    private final String providerName="EXTERNAL_DB_SYNC";
//    private static WebClient.Builder webClinetBuilder;
//
//    public static void setWebClientBuilder(WebClient.Builder builder) {
//        webClinetBuilder = builder;
//    }
//
//    @Override
//    public EventListenerProvider create(KeycloakSession keycloakSession) {
//        return new KeyClockUserSyncProvider(webClinetBuilder);
//    }
//
//    @Override
//    public void init(Config.Scope scope) {
//
//    }
//
//    @Override
//    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {
//
//    }
//
//    @Override
//    public void close() {
//
//    }
//
//    @Override
//    public String getId() {
//        return this.providerName;
//    }
//}
