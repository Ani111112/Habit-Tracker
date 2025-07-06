package org.habittracker.usersync;

import lombok.NoArgsConstructor;
import org.keycloak.Config;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

@NoArgsConstructor
public class KeyclockExternalDbSyncProviderFactory implements EventListenerProviderFactory {
    private final String providerName="EXTERNAL_DB_SYNC";

    @Override
    public EventListenerProvider create(KeycloakSession keycloakSession) {
        System.out.println("Creating EventListenerProvider...");
        return new KeyClockUserSyncProvider(keycloakSession);
    }

    @Override
    public void init(Config.Scope scope) {

    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {

    }

    @Override
    public void close() {

    }

    @Override
    public String getId() {
        return this.providerName;
    }
}
