package swaglabs.functional.api;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PeopleApiTests {

    @Test
    public void validatePeopleApi200ResponseTest() {
        // Given
        given().
                when().get("https://swapi.dev/api/people/?page=2").
                then().assertThat().statusCode(200);
    }
}
