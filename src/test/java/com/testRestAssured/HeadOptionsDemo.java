package com.testRestAssured;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.testRestAssured.Printing_Demo.baseurl;
import static org.hamcrest.Matchers.*;

public class HeadOptionsDemo {
    @Test
    public void headmethod(){
        RestAssured.head(baseurl)
                .then()
                .statusCode(200)
                .header("Server","GitHub.com")
                .body(emptyOrNullString());
    }
    @Test
    public void optionmethod(){
        RestAssured.options(baseurl)
                .then()
                .statusCode(204)
                .header("Server","GitHub.com")
                .header("access-control-allow-methods",equalTo("GET, POST, PATCH, PUT, DELETE"))
                .body(emptyOrNullString());
    }
}
