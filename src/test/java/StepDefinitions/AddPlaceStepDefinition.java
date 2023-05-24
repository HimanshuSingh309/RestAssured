package StepDefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;
import resources.ApiResources;
import resources.TestDataBuild;
import resources.utils;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class AddPlaceStepDefinition extends utils {
	
	TestDataBuild data = new TestDataBuild();
	
	ResponseSpecification resspec;
	RequestSpecification res;
	Response response;
	@Given("^Add place payload with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void add_place_payload_with(String name, String language, String address) throws Throwable {

		  res=given().spec(requestSpecification())
		 .body(data.addPlacePayload(name,language,address));

	}

	@When("^User calls \"([^\"]*)\" with \"([^\"]*)\" http request$")
	public void user_calls_with_http_request(String resources, String method) throws Throwable {
		
		ApiResources resourceAPI =  ApiResources.valueOf(resources);
		System.out.println(resourceAPI.getResources());
		
		 resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		 if(method.equalsIgnoreCase("POST"))
		 response =res.when().post(resourceAPI.getResources());
		 else if (method.equalsIgnoreCase("GET"))
			 response = res.when().get(resourceAPI.getResources());
		 System.out.println("Response is: " + response);
	}

	@Then("^API call got success with status code (\\d+)$")
	public void api_call_got_success_with_status_code(int arg1) throws Throwable {
	   assertEquals(response.statusCode(),200);
	   
	}

	@Then("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	public void in_response_body_is(String Key, String Value) throws Throwable {
		
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		assertEquals(js.get(Key).toString(), Value);
	    
	}



}
