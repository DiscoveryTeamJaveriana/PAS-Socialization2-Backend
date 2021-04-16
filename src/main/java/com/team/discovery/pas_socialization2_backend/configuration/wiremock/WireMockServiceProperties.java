package com.team.discovery.pas_socialization2_backend.configuration.wiremock;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration list mocks services
 *
 * @since 0.0.1
 */
@ConfigurationProperties(prefix = "mocks")
public class WireMockServiceProperties {

    @Getter
    private Map<String, MockProperties> services = new HashMap<>();

    @Getter
    @Setter
    public static class MockProperties {

        /**
         * Indicate if the service is enabled
         */
        private boolean enabled;

        /**
         * Port where the mock service will be listened
         */
        private int port;

        /**
         * Indicates if the port where the mock service will be deployed is secure
         */
        private boolean securePort;

        /**
         * Path where the mock will go to find the resources
         * of the requests and responses
         */
        private String resourcesPath;

    }
}
