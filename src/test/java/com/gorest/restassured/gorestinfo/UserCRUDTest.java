package com.gorest.restassured.gorestinfo;


import com.gorest.restassured.testbase.TestBase;
import com.gorest.restassured.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;


import java.util.HashMap;


import static org.hamcrest.Matchers.hasValue;

/**
 * Created by Jay
 */

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserCRUDTest extends TestBase {

   /* static String email = TestUtils.getRandomValue() + "test1@gmail.com";
    static String name = TestUtils.getRandomValue() + "Prime";
    static String status = "Active";
    static String gender = "Male";*/

    static int userID;

    @Steps
    UsersSteps usersSteps;

    @Title("This will create a new User")
    @Test
    public void test001(){
        ValidatableResponse response = usersSteps.createUser();
        response.statusCode(201);
        userID = response.extract().path("id");

    }
    @Title("Verify get User")
    @Test
    public void test002(){
        ValidatableResponse response = usersSteps.createUser();
        response.statusCode(200);

    }

    @Title("Update the user information the verify the updated information")
    @Test
    public void test003(){

        ValidatableResponse response=usersSteps.updateUser(userID);
        response.statusCode(200);

    }

    @Title("Delete the  student and verify if the student is deleted")
    @Test
    public void test004(){

        usersSteps.deleteUser(userID).statusCode(204);
        usersSteps.getUserByID(userID).statusCode(404);


    }


}
