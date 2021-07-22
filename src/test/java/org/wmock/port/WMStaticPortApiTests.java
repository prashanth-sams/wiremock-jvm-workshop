package org.wmock.port;

import com.github.tomakehurst.wiremock.WireMockServer;
import static io.restassured.RestAssured.*;

import org.junit.jupiter.api.*;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@DisplayName("WireMock with Static Port")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WMStaticPortApiTests
{
    WireMockServer wireMockServer;

    @BeforeAll
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

        stubFor(get(urlEqualTo("/users/1"))
                .inScenario("getFirstUser")
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody("{ \"id\": 1, \"name\": \"Prashanth Sams\", \"gender\": \"male\", \"age\": 31 }")
                )
        );

        stubFor(get(urlEqualTo("/users/3"))
                .inScenario("userNotFound")
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/html")
                        .withStatus(400)
                        .withBody("404 Error")
                )
        );

        stubFor(get(urlEqualTo("/users/4"))
                .inScenario("internalServerError")
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(500)
                        .withBody("500 - Internal Server Error")
                )
        );              

    }

    @Test
    @DisplayName("Get All Users List")
    public void getAllUsersTest()
    {
        given()
        .when()
                .get("http://localhost:8080/users/all")
        .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    @DisplayName("Get First User")
    public void getFirstUserTest()
    {
        given()
        .when()
                .get("http://localhost:8080/users/1")
        .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    @DisplayName("400 Bad Request - User Not Found")
    public void userNotFoundTest()
    {
        given()
        .when()
                .get("http://localhost:8080/users/3")
        .then()
                .assertThat()
                .statusCode(400);
    }

    @Test
    @DisplayName("500 Internal Server Error")
    public void internalServerErrorTest() {
        given()
        .when()
                .get("http://localhost:8080/users/4")
        .then()
                .assertThat()
                .statusCode(500);
    }

    @AfterAll
    void tearDown(){
        wireMockServer.resetAll();
        wireMockServer.stop();
    }
}
