package org.wmock.target;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.*;
import org.wmock.tags.TestConfigValue;

import java.lang.reflect.Method;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;


@DisplayName("From Tag3")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TargetValueMethod
{
    WireMockServer wireMockServer;

    @BeforeAll
    void initWireMock(){
        wireMockServer = new WireMockServer(WireMockConfiguration.options().dynamicPort());
        wireMockServer.start();
        initStub();
    }

    @TestConfigValue(level = "Level A")
    public void initStub() {
        Method[] methods = TargetValueMethod.class.getMethods();
        for (Method m : methods) {
            if (m.isAnnotationPresent(TestConfigValue.class)) {
                TestConfigValue at = m.getAnnotation(TestConfigValue.class);
                System.out.println(at.level());
            }
        }
        configureFor("localhost", wireMockServer.port());

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
    @DisplayName("Get All Users List")
    public void getAllUsersTest()
    {
        given()
        .when()
                .get("http://localhost:"+wireMockServer.port()+"/users/all")
        .then()
                .assertThat()
                .statusCode(200);
    }

    @AfterAll
    void tearDown(){
        wireMockServer.resetAll();
        wireMockServer.stop();
    }
}
