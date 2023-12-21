package restassured;


import dto.ContactDTO;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

import static org.hamcrest.Matchers.containsString;

public class UpdateContactTests extends helpers.RestAssuredHelper {
    Random random = new Random();
    int i = random.nextInt(10);
    ContactDTO contactDTO;
    String id = "8176457b-71bc-4f4a-a83f-5b81217ebd58";
    String endpoint = "contacts";


    @BeforeMethod
    public void precondition(){
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = PATH;

        contactDTO = ContactDTO.builder()
                .name("QA38")
                .lastName("Automation")
                .email("qa38_" + i + "@mail.com")
                .phone("12345678" + i)
                .address("Rehovot")
                .description("Students")
                .build();

        String message = RestAssured.
                given()
                .header("Authorization", "Bearer " + getToken())
                .body(contactDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .path("message");

        id = message.substring(message.lastIndexOf(' ') + 1);
    }

    @Test
    public void updateContactPositive(){

        contactDTO.setId(id);
        contactDTO.setName("QA_38_Updated");

        RestAssured
                .given()
                .log().all()
                .header("Authorization", "Bearer " + getToken())
                .body(contactDTO)
                .contentType(ContentType.JSON)

                .when()
                .put(endpoint)
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("message", containsString("Contact was updated"));
    }

}