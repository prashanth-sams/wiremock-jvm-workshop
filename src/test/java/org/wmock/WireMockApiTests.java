package org.wmock;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockApiTests
{
    WireMockServer wireMockServer;

    @BeforeEach
    void initWireMock(){
        wireMockServer = new WireMockServer(7777);
        wireMockServer.start();
        initStub();
    }

    private void initStub() {
        configureFor("localhost", 7777);

        stubFor(get(urlEqualTo("/users/all"))
                .inScenario("getAllUsers")
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody("")
                )
        );
    }

    @Test
    public void firstMethod()
    {
        System.out.println("am here");
    }
}
