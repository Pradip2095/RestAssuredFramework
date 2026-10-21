package Day3;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class HeadersDemo {


    @Test
    void headersTest(){

        given()
                .when()
                . get("https://www.google.com/")
                .then()
                .statusCode(200)
                .header("content-type", "text/html; charset=ISO-8859-1")
                .header("Accept-Ch", "Sec-CH-Prefers-Color-Scheme")
                .header("Cache-Control", "private, max-age=0");

    }


    @Test
    void getHeadersTest(){
       Response res = given()
                .when()
                .get("https://www.google.com/");


       //get single header
//        String headerValue = res.getHeader("content-type");
//        System.out.println("header content: " + headerValue);

        //get all headers info
        Headers headersValues = res.getHeaders();
        for(Header hd: headersValues){
            System.out.println(hd.getName() + "  " + hd.getValue());
        }
    }
}
