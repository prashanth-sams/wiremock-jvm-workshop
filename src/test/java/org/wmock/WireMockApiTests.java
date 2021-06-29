package org.wmock;

import com.github.tomakehurst.wiremock.WireMockServer;
import static io.restassured.RestAssured.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockApiTests
{
    WireMockServer wireMockServer;

    @BeforeEach
    void initWireMock(){
        wireMockServer = new WireMockServer(8080);
        wireMockServer.start();
        initStub();
    }

    private void initStub() {
        configureFor("localhost", 8080);

        stubFor(get(urlEqualTo("/users/all"))
                .inScenario("getAllUsers")
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody("{ \"id\": 1, \"name\": \"Prashanth Sams\", \"gender\": \"male\", \"age\": 31 }, { \"id\": 2, \"name\": \"John Smith\", \"gender\": \"male\", \"age\": 40 }")
                )
        );
    }

    @Test
    public void getAllUsersTest()
    {
        given()
                .when()
                    .get("http://localhost:8080/users/all")
                .then()
                    .assertThat()
                    .statusCode(200);
    }

    @AfterEach
    void resetMock(){
        wireMockServer.resetAll();
        wireMockServer.stop();
    }
}
