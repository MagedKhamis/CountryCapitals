package restCountries;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Scanner;

import static io.restassured.RestAssured.given;

public class CountriesCapital {


    public static String searchByName() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the country Name");
        String countryName = scan.nextLine();

        Response res = given() // pre-condition
                .baseUri("https://restcountries.com/")
                .when()
                .get("v3.1/name/" + countryName)
                .then()
                .extract().response();
        JsonPath js = res.jsonPath();
        String capitalcity = js.getString("capital[0]");

        return capitalcity;
    }

    public static void searchByNameNegative() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the wrong country Name");
        String countryName = scan.nextLine();

        Response res = given() // pre-condition
                .baseUri("https://restcountries.com/")
                .when()
                .get("v3.1/name/" + countryName)
                .then()
                .extract().response();

      int statusCode =   res.statusCode();
      if (statusCode==200){
          System.out.println("We are good");
      }
      else if (statusCode==404){
          System.out.println("Check the country name");
      }
        JsonPath js = res.jsonPath();
        String errorMessage = js.getString("message");
        Assert.assertEquals(errorMessage, "Not Found");


    }
    public static String searchByCode() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the country code");
        String countryCode = scan.nextLine();

        Response res = given()
                .baseUri("https://restcountries.com")
                .when()
                .get("/v3.1/alpha/" + countryCode)
                .then()
                .extract().response();
        JsonPath js = res.jsonPath();
        String capitalcity = js.getString("capital[0]");
        return  capitalcity;
    }





    public static void main(String[] args) {
        System.out.println(searchByName());
        System.out.println(searchByCode());


        searchByNameNegative();


    }


}