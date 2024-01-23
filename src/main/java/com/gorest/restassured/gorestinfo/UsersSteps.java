package com.gorest.restassured.gorestinfo;

import com.gorest.restassured.constants.EndPoints;
import com.gorest.restassured.constants.Path;
import com.gorest.restassured.model.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.util.HashMap;
import java.util.List;

import static com.gorest.restassured.utils.TestUtils.getRandomValue;

/**
 * Created by Jay
 */

public class UsersSteps {

    @Step("Creating student with name: {0}, email : {1}, gender : {2} status :{3}")
    public ValidatableResponse createUser(){

        UserPojo userPojo = new UserPojo();
        userPojo.setName("Prime");
        userPojo.setEmail("test" + getRandomValue() + "@gmail.com");
        userPojo.setGender("male");
        userPojo.setStatus("active");

        return SerenityRest.given()
                .body(userPojo)
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer 10efcc0eda0dc46c3d8b3d415af61bad8412b32398edede555b02a9ab8860eb4")
                .body(userPojo)
                .when()
                .post(Path.USERS)
                .then().statusCode(201).log().all();
    }

    @Step("Verify User Created with UserID : {0}")
    public HashMap<String, Object> getUserInfoByUserID(int userID) {
        /*String s1 = "findAll{it.userID == '";
        String s2 = "'}.get(0)";
*/
        return SerenityRest.given().log().all()
                .when()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer 10efcc0eda0dc46c3d8b3d415af61bad8412b32398edede555b02a9ab8860eb4")
                .pathParam("userID",userID)
                .when()
                .get(Path.USERS + EndPoints.GET_SINGLE_USER_BY_ID)
                .then().statusCode(200).extract().path("");
    }

    @Step("Updating User with UserID :{0}, name : {1},email :{2} , gender : {3}, status : {4}")
    public ValidatableResponse updateUser(int userID) {
        UserPojo userPojo = new UserPojo();
        userPojo.setName("ABC XYZ MNOP");
        userPojo.setEmail(getRandomValue() + "@gmail.com");
        userPojo.setGender("male");
        userPojo.setStatus("active");
        return SerenityRest.given().log().all()
                .header("Authorization", "Bearer c8fa4af5449aff5c104930482cc994318f2ae1a097c32bb596799dae3b2f6f3b")
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .pathParam("userID", userID)
                .when()
                .body(userPojo)
                .put(Path.USERS + EndPoints.UPDATE_USER_BY_ID)
                .then().log().all();
    }

    @Step("Deleting the user information with userID: {0}")
    public ValidatableResponse deleteUser(int userID) {
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer 10efcc0eda0dc46c3d8b3d415af61bad8412b32398edede555b02a9ab8860eb4")
                .pathParam("userID", userID)
                .when()
                .delete(Path.USERS+EndPoints.DELETE_USER_BY_ID)
                .then();
    }

    @Step("Getting the user information with userID: {0}")
    public ValidatableResponse getUserByID(int userID){

            return SerenityRest.given().log().all()
                    .pathParam("userID", userID)
                    .when()
                    .get(Path.USERS + EndPoints.GET_SINGLE_USER_BY_ID)
                    .then();
        }
    }



