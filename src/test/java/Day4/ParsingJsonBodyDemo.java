package Day4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ParsingJsonBodyDemo {
    Response res;
    @Test
    void ParsingJsonBodyPractice(){

        //Approach 1
        /*given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/store")
                .then()
                .statusCode(200)
                .header("content-Type", "application/json; charset=utf-8")
                .body("book[3].title", equalTo("The Lord of the Rings"));*/

        //Approach 2
        /*res =  given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/store");

        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.header("content-Type"), "application/json; charset=utf-8");
        String bookName = res.jsonPath().get("book[3].title").toString();
        Assert.assertEquals(bookName, "The Lord of the Rings");*/


        //Approach 3 : Using JSONObject Class
        boolean status = false;
        res =  given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/store");

        String body = res.getBody().asString();
        JSONObject jo = new JSONObject(body); // Convert response to json object
        //JSONObject jo = new JSONObject(res.getBody().asString());
        for(int i=0; i<jo.getJSONArray("book").length(); i++){
           String bookTitle =  jo.getJSONArray("book").getJSONObject(i).get("title").toString();
           if(bookTitle.equals("The Lord of the Rings")){
               status = true;
               break;
           }
            System.out.println("Book Title is : " + bookTitle);
        }


        //Validate the total price
        double totalPrice = 0;
        for(int i = 0; i<jo.getJSONArray("book").length(); i++){

            String price = jo.getJSONArray("book").getJSONObject(i).get("price").toString();
            totalPrice = totalPrice + Double.parseDouble(price);
        }

        System.out.println("Total Price is: " + totalPrice);
        Assert.assertEquals(totalPrice, 701.25);


        //Validate the author name

        for(int i=0; i<jo.getJSONArray("book").length(); i++){

            String authorName = jo.getJSONArray("book").getJSONObject(i).get("author").toString();
            if(authorName.equals("Evelyn Waugh")){
                System.out.println("Author Name is: " + authorName);
                break;
            }
        }

    }
}
