package helpers;

import dto.AuthRequestDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RestAssuredHelper {

    public  String BASE_URI = "https://contactapp-telran-backend.herokuapp.com";
    public String PATH = "v1";

    public String authHeader = "Authorization";
    public String getToken(){

        AuthRequestDTO loginBody = AuthRequestDTO.builder()
                .username("abc@def.com")
                .password("$Abcdef12345")
                .build();

        String login_url = "https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword";
        String token = RestAssured
                .given()
                    .log().all()
                .when()
                    .contentType(ContentType.JSON)
                    .body(loginBody)
                    .post(login_url)
                .then()
                    .log().all()
                    .statusCode(200)
                    .extract().response().jsonPath().getString("token");
        return token;

    }


}
