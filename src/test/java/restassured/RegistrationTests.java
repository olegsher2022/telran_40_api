package restassured;

import dto.AuthRequestDTO;

import helpers.RestAssuredHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.Random;

import static org.hamcrest.Matchers.containsString;

public class RegistrationTests extends RestAssuredHelper {
    Random random = new Random();
    int i = random.nextInt(10);
    String endpoint = "user/registration/usernamepassword";

    @BeforeMethod
    public void precondition(){
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = PATH;
    }

    @Test
    public void registrationPositive(){

        AuthRequestDTO auth = AuthRequestDTO.builder()
                .username("QA38_" + i + "@mail.com")
                .password("$Abcdef12345")
                .build();

        String token = RestAssured
                .given()
                .body(auth)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .path("token");

        System.out.println(token);
    }

    @Test
    public void registrationNegativeWrongEmail(){

        AuthRequestDTO auth = AuthRequestDTO.builder()
                .username("QA38_" + i + "mail.com")
                .password("$Abcdef12345")
                .build();

        RestAssured
                .given()
                .body(auth)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(400)
                .assertThat().body("message.username", containsString("must be a well-formed email address"));
    }



}