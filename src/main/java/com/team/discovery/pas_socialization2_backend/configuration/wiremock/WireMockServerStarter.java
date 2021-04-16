package com.team.discovery.pas_socialization2_backend.configuration.wiremock;

import static java.util.Objects.nonNull;

import java.net.URL;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ClasspathFileSource;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import lombok.extern.slf4j.Slf4j;

/**
 * The server to start the mocks services
 *
 * @since 0.0.1
 */
@Slf4j
public class WireMockServerStarter {

    private static final String SPLIT_MARK = "!";

    private final WireMockServiceProperties mockServiceProperties;

    public WireMockServerStarter(final WireMockServiceProperties mockServiceProperties) {

        this.mockServiceProperties = mockServiceProperties;
    }

    public void startMocks() {

        if (nonNull(mockServiceProperties)) {
            mockServiceProperties.getServices().forEach(this::startMockService);
        }
    }

    private void startMockService(final String nameService, final WireMockServiceProperties.MockProperties mockProperties) {

        if (mockProperties.isEnabled()) {

            log.info("Creating mock: [{}] to see the request go to: {}://localhost:{}/__admin/",
                    nameService, mockProperties.isSecurePort() ? "https" : "http", mockProperties.getPort());
            new WireMockServer(mockProperties.isSecurePort() ?
                    buildSecurePortMockProperties(mockProperties) : buildMockProperties(mockProperties)).start();
        }
    }

    private WireMockConfiguration buildMockProperties(final WireMockServiceProperties.MockProperties mockProperties) {

        return WireMockConfiguration.options()
                .usingFilesUnderClasspath(mockProperties.getResourcesPath())
                .fileSource(new ClasspathFileSource(getPathResources(mockProperties.getResourcesPath())))
                .asynchronousResponseEnabled(true)
                .extensions(new ResponseTemplateTransformer(true))
                .port(mockProperties.getPort());
    }

    private WireMockConfiguration buildSecurePortMockProperties(final WireMockServiceProperties.MockProperties mockProperties) {

        return WireMockConfiguration.options()
                .usingFilesUnderClasspath(mockProperties.getResourcesPath())
                .fileSource(new ClasspathFileSource(getPathResources(mockProperties.getResourcesPath())))
                .asynchronousResponseEnabled(true)
                .extensions(new ResponseTemplateTransformer(true))
                .httpsPort(mockProperties.getPort())
                .httpDisabled(true);
    }

    private  String getPathResources(final  String folderMock){

        final URL url = getClass().getClassLoader().getResource(folderMock);
        String pathStubs = folderMock;

        if (nonNull(url)) {
            final String[] arrayPartitionPath = url.toExternalForm().split(SPLIT_MARK);
            if(arrayPartitionPath.length == 3){

                final String pathResourcesMock = arrayPartitionPath[1]+arrayPartitionPath[2];
                pathStubs = (pathResourcesMock).substring(1);
            }
        }
        return pathStubs;
    }
}
