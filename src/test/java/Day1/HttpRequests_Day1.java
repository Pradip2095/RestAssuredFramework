package Day1;
/*
given()--> content type, set cookies, add auth, add params, set headers info etc...
when()--> get, post, put, delete
then()--> validate status code, extract response, extract headers cookies & response body
 */

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;


public class HttpRequests_Day1 {

    int id;

    @Test(priority = 1)
    public void getUsers() {

        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .log().all();
    }

    @Test(priority = 2)
    public void createUser() {
        HashMap data = new HashMap();
        data.put("name", "pradip");
        data.put("job", "engineer");

            id = given()
                .contentType("application/json")
                .body(data)

            .when()
                 .post("https://reqres.in/api/users")
                 .jsonPath().getInt("id");
//            .then()
//                 .statusCode(201)
//                 .log().all();
    }


    @Test(priority = 3, dependsOnMethods = {"createUser"})
    public void updateUser() {
        HashMap data = new HashMap();
        data.put("name", "ravindra");
        data.put("job", "swimmer");

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .put("https://reqres.in/api/users/"+id)
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 4)
    public void deleteUser(){
        given()

                .when()
                .delete("https://reqres.in/api/users/"+id)
                .then()
                .statusCode(204)
                .log().all();

    }

}
