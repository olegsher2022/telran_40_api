package restassured;
import dto.AuthRequestDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.anything;
import java.net.URI;


public class LoginTests {
    @Test
    public void positiveLogin(){
//        String loginBody = "{\n" +
//                "   \"username\": \"abc@def.com\", \n" +
//                "   \"password\": \"$Abcdef12345\" \n" +
//                "}";
        AuthRequestDTO loginBody = AuthRequestDTO.builder()
                .username("abc@def.com")
                .password("$Abcdef12345")
                .build();

        String login_url = "https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword";
        RestAssured
                .given()
                    .log().all()
                .when()
                    .contentType(ContentType.JSON)
                    .body(loginBody)
                    .post(login_url)
                .then()
                    .log().all()
                    .statusCode(200)
                    .assertThat().body("token", anything())
                    .extract().response().jsonPath().getString("token");



    }





}
