FROM quay.io/keycloak/keycloak:26.2.1

COPY target/key-clock-spi-service.jar /opt/keycloak/providers/key-clock-spi-service.jar

RUN /opt/keycloak/bin/kc.sh build

ENTRYPOINT ["/opt/keycloak/bin/kc.sh"]
CMD ["start-dev", "--spi-events-listener-EXTERNAL_DB_SYNC-enabled=true"]