package Day3;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CookiesDemo {

    @Test(priority = 1)
    void testCookies() {
        given()

                .when()
                .get("https://www.google.com/")
                .then()
                .statusCode(200)
                .cookie("AEC", "Aaa9EJpIsbvXWQ2Rpyv1BhG6jufa49en37s5pFQR_I4TRN8LB4e1RuuE9Q")
                .log().all();
    }


    @Test(priority = 2)
    void getCookies() {
        Response res = given()
                .when()
                .get("https://www.google.com/");

        //get single cookie
        String cookies = res.getCookie("AEC");
        System.out.println("Cookie Value: " + cookies);

        //get multiple cookies
        Map<String, String> allCookies = res.getCookies();
        System.out.println(allCookies.keySet());

        for(String k:allCookies.keySet()){
            String cookieValue = res.getCookie(k);
            System.out.println(k +"   " + cookieValue);
        }
    }
}
