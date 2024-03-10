package alas.stepDefinitions;

import alas.pojos.User;
import alas.utilities.ApiUtils;
import alas.utilities.ConfigurationPropertiesReader;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;


import static io.restassured.RestAssured.*;

public class ApiStepDef {

    User user = ApiUtils.createUser();

    Response response;

    int userId;

    Response response1;


    Faker faker = new Faker();

    //Bearer token which is needed for every request
    final String bearerToken = "Bearer 36afc66576460a208d90dda804ae1a8537f48ecdfcfb6740db57c5442e6821e4";

    @When("setting baseURI")
    public void setting_base_uri() {

        //baseUri which is passed before every scenario (Background step)
        baseURI = ConfigurationPropertiesReader.getProperty("baseUri");

    }
    @When("user sends post request")
    public void user_sends_post_request() {

        response = given().header("Authorization", bearerToken).
                accept(ContentType.JSON).
                and().contentType(ContentType.JSON)
                .and().body(user)
                .when()
                .post();

        response.prettyPrint();

        //getting user's id which we can later on use as an endpoint to retrieve, update or delete user
        userId = response.path("id");

    }
    @Then("verify that new user is create and status code is <{int}>")
    public void verify_that_new_user_is_create_and_status_code_is(int int1) {

        //verifying that name is the same as the one we sent in request
        assertThat(response.path("name"), is(user.getName()));

        assertThat(response.path("email"), is(user.getEmail()));

        assertThat(response.path("gender"), is(equalToIgnoringCase(user.getGender())));

        assertThat(response.path("status"), is(user.getStatus()));

        //verifying that status code is as expected
        assertThat(response.statusCode(), is(int1));


    }

    @Then("verify that user can retrieve newly created user")
    public void verify_that_user_can_retrieve_newly_created_user() {

        //using previously created user's id in get request, to verify that data is successfully transferred
      User userPosted =  given().header("Authorization", bearerToken).
              accept(ContentType.JSON)
                .and()
                .get(String.valueOf(userId))
                .then()
                .statusCode(200)
                .extract().as(User.class);

      assertThat(userPosted.getName(), is(user.getName()));

      assertThat(userPosted.getEmail(), is(user.getEmail()));

      assertThat(userPosted.getGender(),is(equalToIgnoringCase(user.getGender())));

      assertThat(userPosted.getStatus(), is(user.getStatus()));

    }

    @When("user sends put request to update user's details")
    public void user_sends_put_request_to_update_user_s_details() {

        //changing the fields of previously created user
        user.setStatus("inactive");

        user.setEmail(faker.internet().emailAddress());

        //sending put request to update user's details
        response = given().header("Authorization", bearerToken).
                accept(ContentType.JSON).
                and().contentType(ContentType.JSON)
                .and().body(user)
                .when()
                .put(String.valueOf(userId));

        response.prettyPrint();

    }

    @Then("verify that status code is <{int}> user details are updated correctly")
    public void verify_that_status_code_is_user_details_are_updated_correctly(Integer int1) {

        //verifying that we are getting correct status code after update
        assertThat(response.statusCode(), is(int1));

        userId = response.path("id");

        User userPosted2 = given().header("Authorization", bearerToken).
                accept(ContentType.JSON)
                .and()
                .get(String.valueOf(userId))
                .then()
                .statusCode(200)
                .extract().as(User.class);

        //verifying that fields are updated accordingly
        assertThat(userPosted2.getStatus(), is(user.getStatus()));

        assertThat(userPosted2.getEmail(), is(user.getEmail()));

    }

    @When("user sends delete request to delete user")
    public void user_sends_delete_request_to_delete_user() {

            //deleting previously created user
     response1 = given().header("Authorization", bearerToken).
                accept(ContentType.JSON)
                .and()
                .delete(String.valueOf(userId));

     response1.prettyPrint();

    }
    @Then("verify that status code is <{int}> and that user cannot retrieve previously deleted user")
    public void verify_that_status_code_is_and_that_user_cannot_retrieve_previously_deleted_user(Integer int1) {

        //verifying status code is correct
        assertThat(response1.statusCode(), is(int1));

        //sending get request to previously deleted user to check if it still exists
        Response response2 = given().header("Authorization", bearerToken).
                accept(ContentType.JSON)
                .and()
                .get(String.valueOf(userId));

        response2.prettyPrint();

        //verifying message after trying to retrieve previously deleted user
        assertThat(response2.path("message"),is("Resource not found"));

    }






}
