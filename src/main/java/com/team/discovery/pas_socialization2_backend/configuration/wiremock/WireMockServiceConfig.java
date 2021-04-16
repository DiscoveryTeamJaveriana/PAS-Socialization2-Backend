package com.team.discovery.pas_socialization2_backend.configuration.wiremock;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The configuration class to mocks services
 *
 * @since 0.0.1
 */
@Configuration
@EnableConfigurationProperties(WireMockServiceProperties.class)
@ConditionalOnProperty(name = "mocks.enabled", havingValue = "true")
public class WireMockServiceConfig {

    @Bean
    public WireMockServerStarter createMocks(final WireMockServiceProperties mockServiceProperties) {

        final WireMockServerStarter wireMockServerStarter = new WireMockServerStarter(mockServiceProperties);
        wireMockServerStarter.startMocks();
        return wireMockServerStarter;
    }
}
