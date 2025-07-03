FROM quay.io/keycloak/keycloak:26.2.1

COPY target/key-clock-spi-service.jar /opt/keycloak/providers/key-clock-spi-service.jar

ENTRYPOINT ["/opt/keycloak/bin/kc.sh"]
CMD ["start-dev"]