package Day3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoggingDemo {

    @Test
    public void testLogs() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                //.log().body();
                //.log().headers();
                //.log().cookies();
        .log().all();

    }
}
