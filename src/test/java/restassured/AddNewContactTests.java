package restassured;

import dto.ContactDTO;

import helpers.RestAssuredHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Random;

import static org.hamcrest.Matchers.startsWith;


public class AddNewContactTests extends RestAssuredHelper {

    String endpoint = "contacts";

    @BeforeMethod
    public void precondition(){
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = PATH;
    }

    @Test
    public void addNewContactPositive(){
        Random random = new Random();
        int i = random.nextInt(10);
        ContactDTO contactDTO = ContactDTO.builder()
                .name("QA38")
                .lastName("Automation")
                .email("qa38_" + i + "@mail.com")
                .phone("1234567890")
                .address("Rehovot")
                .description("Students")
                .build();

        RestAssured
                .given()
                .header("Authorization", "Bearer " + getToken())
                .body(contactDTO)
                .contentType(ContentType.JSON)
                .when()
                    .post(endpoint)
                .then()
                    .log().all()
                    .statusCode(200)
                    .assertThat().body("message",startsWith("Contact was added!"));





    }

}