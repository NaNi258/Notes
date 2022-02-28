package com.testRestAssured;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.testRestAssured.Printing_Demo.baseurl;
import static org.hamcrest.Matchers.*;

public class Nestedbody {
    @Test
    public void nestedbodydemo(){
        RestAssured.get(baseurl+"rate_limit")
                .then()
                .rootPath("resources.core")//for settingnew root path
                    .body(".limit", equalTo(60))
                    .body(".remaining",lessThanOrEqualTo(60))
                    .body(".reset",notNullValue())
                .rootPath("resources.search")//for new root path
                    .body(".reset",notNullValue())
                .noRootPath()//to reset the path
                .body("resources.graphql.limit",equalTo(0));
    }
    @Test
    public void repeating(){
        RestAssured.get("https://reqres.in/api/users/?page=2")
                .then()
                .body("data.id[0]",equalTo(7))
                .body("data.first_name",contains("Michael","Lindsay","Tobias","Byron","George","Rachel"))//to check all the item in same order
                .body("data.first_name",containsInAnyOrder("Lindsay","Michael","Tobias","Byron","George","Rachel"))
                .body("data.first_name",hasItem("Michael"))//for checking single item is present or not
                .body("data.first_name",hasItems("Lindsay","Tobias"))//for checking multiple  item but  need not to all item is present or not
                .body("data.first_name",hasItems(endsWith("l")));
    }
}
