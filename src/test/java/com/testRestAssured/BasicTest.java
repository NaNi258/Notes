package com.testRestAssured;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class BasicTest {
@Test
    public void sometest(){
    RestAssured.get("https://api.github.com/")
            .then()
            .statusCode(200);
}
}
