package restassured;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestGoogle {
    @Test
	public void makeSureThatGoogleIsUp() {
		 RestAssured.given().when().get("http://www.google.com").then().assertThat().statusCode(200);
	 }
	@Test
	public void makeSureThatGoogleIsUp_explained() {
		 RestAssured
				 .given()
				 .when()
				 	.get("http://www.google.com")
				 .then()
				 	.assertThat().statusCode(400);
	 }
}
