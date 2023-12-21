package restassured;

import dto.ContactDTO;
import dto.ContactListDTO;
import helpers.RestAssuredHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetAllContactsTests extends RestAssuredHelper {

    @Test
    public void getAllContacts(){
        String login_url = "https://contactapp-telran-backend.herokuapp.com/v1/contacts";
        ContactListDTO contacts = RestAssured
                .given()
                    .log().all()
                .when()
                    .contentType(ContentType.JSON)
                    .header("Authorization", "Bearer " + getToken())
                    .get(login_url)
                .then()
//                    .log().all()
                    .assertThat().statusCode(200)
                    .extract()
                    .as(ContactListDTO.class);

        for(ContactDTO contactDTO : contacts.getContacts()){
            System.out.println(contactDTO.getId());
            System.out.println(contactDTO.getName());
        }

        Assert.assertFalse(contacts.toString().isBlank(), "abc");

    }

}
