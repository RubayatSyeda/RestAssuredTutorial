package org.example;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Get_Post_Examples {
    @Test
    public void testGet() {
        baseURI = "https://reqres.in/api/";
        get("users?page=2")
                .then()
                .statusCode(200)
                .body("data[1].id", equalTo(8))
                .body("data.first_name", hasItem("Lindsay"))
                .body("data[1].first_name", equalTo("Lindsay"));
    }

    @Test
    public void testPost() {
        baseURI = "https://reqres.in/api/";
        given()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}")
                .when()
                .post("users")
                .then()
                .statusCode(201)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("leader"));
    }

    @Test
    public void testPostUsingMap(){
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "Debopriya Deb Roy");
//        map.put("job", "Software Engineer");


        JSONObject request = new JSONObject();
        request.put("name", "Syeda Rubayat");
        request.put("job", "QA Tester");
        System.out.println(request.toJSONString());

        baseURI = "https://reqres.in/api/";
        given()
                .header("Content-Type", "application/json")
                .body(request.toJSONString())
                .when()
                .post("users")
                .then()
                .statusCode(201)
                .body("name", equalTo("SyedaRubayat"))
                .body("job", equalTo("QA Tester"));

    }
}