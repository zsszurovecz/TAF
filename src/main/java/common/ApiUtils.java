package common;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import java.util.Random;

public class ApiUtils {

    private static final int COUNT_OF_PEOPLE = 82;
    private static final String BASEURL = "https://swapi.dev/api/";
    private static String planetID;

    private static ResponseBody getRandomSWPeopleAPI() {
        RestAssured.baseURI = BASEURL;
        RequestSpecification httpRequest = RestAssured.given();
        Random rnd = new Random();
        Response response = httpRequest.get("/people/" + rnd.nextInt(1, COUNT_OF_PEOPLE + 1) + "/");
        return response.getBody();
    }

    private static ResponseBody getSWPlanetAPI() {
        RestAssured.baseURI = BASEURL;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/planets/" + planetID + "/");
        return response.getBody();
    }

    public static String getRandomZip() {
        String bodyString = getSWPlanetAPI().asString();
        System.out.println(bodyString);
        String nameStartText = bodyString.substring(bodyString.indexOf(":") + 2);
        String name = nameStartText.substring(0, nameStartText.indexOf('"'));
        return name;
    }

    public static String getRandomFullName() {
        String bodyString = getRandomSWPeopleAPI().asString();
        System.out.println(bodyString);
        String fullNameStartString = bodyString.substring(bodyString.indexOf(":") + 2);
        String fullName = fullNameStartString.substring(0, fullNameStartString.indexOf('"'));
        String homeWorldStartString = bodyString.substring(bodyString.indexOf("homeworld") + 42);
        planetID = homeWorldStartString.substring(0, homeWorldStartString.indexOf('/'));
        return fullName;
    }
}
