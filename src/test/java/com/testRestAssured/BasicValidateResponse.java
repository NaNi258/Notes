package com.testRestAssured;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.testRestAssured.Printing_Demo.baseurl;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BasicValidateResponse {
    @Test
    public void basicvalidate(){
        ValidatableResponse x = RestAssured.get(baseurl)
                .then()
                .assertThat()
                    .statusCode(200)
                .and().assertThat()
                    .header("X-XSS-Protection","0");
    }
    @Test public void hamcrestdemo(){
        assertThat(10,lessThan(11));
    }


    @Test
    public void simpleHamcrestMatcher(){
        RestAssured.get(baseurl)
                .then()
                .statusCode(200)
                .statusCode(Matchers.lessThan(250))
                .header("Cache-Control",Matchers.containsStringIgnoringCase("X-age=60"))
                .time(Matchers.lessThan(3L), TimeUnit.SECONDS)
                .header("etag",notNullValue())
                .header("etag",not(emptyString()));

    }
    @Test
    public void complexHamcrestacmacther(){
        RestAssured.get(baseurl)
                .then()
                .header("X-RateLimit-Limit",Integer::parseInt, equalTo(60))
//                .header("Date",date-> LocalDate.parse((CharSequence) date,DateTimeFormatter.RFC_1123_DATE_TIME).OrderingComparison.comparesEqualTo(LocalDate.now()))
                .header("X-RateLimit-Limit",response->greaterThan(response.header("X-RateLimit-Remaining")));
    }
    Map<String,String> expectedheaders=Map.of("Content-Encoding","gzip",
                                                "Access-Control-Allow-Origin","*"
                                                            );
@Test
    public void usingMaptoTestHeaders(){
        RestAssured.get(baseurl)
                .then()
                .headers(
                        "Server","GitHub.com",
                        "Content-Type","application/json; charset=utf-8",
                        "Cache-Control",Matchers.containsStringIgnoringCase("s-maxage=60")
                        )
                .headers(expectedheaders);
    }
}
