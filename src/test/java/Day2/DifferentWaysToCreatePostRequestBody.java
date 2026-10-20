package Day2;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

/*
Post Request Body using HashMap
Post Request Body using Org.json library
Post Request Body using POJO(Plain Old Java Object)
Post Request Body using external JSON file

 */
public class DifferentWaysToCreatePostRequestBody {

    //Post Request Body using HashMap

//    @Test(priority = 1)
    public void createPostRequestUsingHashMap(){
        //Create A test data for Post Request
        HashMap data = new HashMap();
        data.put("name", "Pradip");
        data.put("location", "India");
        data.put("phone", "123456789");
        String coursesArray[] = {"C", "C++"};
        data.put("courses", coursesArray);


        //RestAssured Test Script
        given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .body("name", equalTo("Pradip"))
                .body("location", equalTo("India"))
                .body("phone", equalTo("123456789"))
                .body("courses[0]", equalTo("C"))
                .body("courses[1]", equalTo("C++"))
                .header("content-type", "application/json; charset=utf-8")
                .log().all();
    }

    //Post Request Body using Org.json library
    //@Test(priority = 1)
    public void createPostRequestUsingOrgJson(){
        //Create A test data for Post Request
        JSONObject data = new JSONObject();
        data.put("name", "Pradip");
        data.put("location", "India");
        data.put("phone", "123456789");
        String coursesArray[] = {"C", "C++"};
        data.put("courses", coursesArray);


        //RestAssured Test Script
        given()
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .body("name", equalTo("Pradip"))
                .body("location", equalTo("India"))
                .body("phone", equalTo("123456789"))
                .body("courses[0]", equalTo("C"))
                .body("courses[1]", equalTo("C++"))
                .header("content-type", "application/json; charset=utf-8")
                .log().all();
    }

    // Post Request Body using POJO
    //@Test(priority = 1)
    public void createPostRequestUsingPOJO(){
        //Create A test data for Post Request
        Pojo_PostRequest data = new Pojo_PostRequest();
        data.setName("Pradip");
        data.setLocation("India");
        data.setPhone("123456789");
        String[] coursesArray = {"C", "C++"};
        data.setCourses(coursesArray);


        //RestAssured Test Script
        given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .body("name", equalTo("Pradip"))
                .body("location", equalTo("India"))
                .body("phone", equalTo("123456789"))
                .body("courses[0]", equalTo("C"))
                .body("courses[1]", equalTo("C++"))
                .header("content-type", "application/json; charset=utf-8")
                .log().all();
    }


    @Test(priority = 1)
    public void createPostRequestUsingExternalJsonFile() throws FileNotFoundException {
        //Create A test data for Post Request
        File f = new File(".\\body.json");
        FileReader fr = new FileReader(f);
        JSONTokener jt = new JSONTokener(fr);
        JSONObject data = new JSONObject(jt);

        //RestAssured Test Script
        given()
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .body("name", equalTo("Pradip"))
                .body("location", equalTo("India"))
                .body("phone", equalTo("123456789"))
                .body("courses[0]", equalTo("C"))
                .body("courses[1]", equalTo("C++"))
                .header("content-type", "application/json; charset=utf-8")
                .log().all();
    }


    //deleting post request
    @Test(priority = 2)
    public void deletePostRequest(){
        given()
                .when()
                .delete("http://localhost:3000/students/5")
                .then()
                .statusCode(200);
    }
}
