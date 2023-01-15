package com.lms.api.stepDefinition;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.lms.api.utilities.ConfigReader;
import com.lms.api.utilities.ExcelReader;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ProgramPost_SD {
	RequestSpecification requestSpec;
	Response response;
	String path;
	String sheetPost;
	String ProgramId;

	ExcelReader excelReaderUtil;
	Scenario scenario;
	Properties properties;

	private static final Logger logger = LogManager.getLogger(ProgramPost_SD.class);

	public ProgramPost_SD() {
		ConfigReader config = new ConfigReader();
		properties = config.loadProperties();
	}

	@Before
	public void initializeDataTable(Scenario scenario) throws Exception {
		this.scenario = scenario;
		sheetPost = properties.getProperty("sheetPost");
		excelReaderUtil = new ExcelReader(properties.getProperty("programExcelpath"));
		excelReaderUtil.readSheet(sheetPost);
		logger.info(" sheetPost" +sheetPost);		
		logger.info(" programExcelpath " + properties.getProperty("programExcelpath"));
	}

	public void requestSpecificationPost() throws Exception {
		requestSpec.header("Content-Type", "application/json");
		logger.info("scenario.getName: "+scenario.getName());
		String bodyExcel = excelReaderUtil.getDataFromExcelPost(scenario.getName(), "Body");
		//assertThat("Schema Validation Failed", bodyExcel, matchesJsonSchemaInClasspath("PorgramPost.json"));
		requestSpec.body(bodyExcel).log().all();
        response = requestSpec.when().post(path);
	}

	@Given("User is on Post Method with endpoint")
	public void user_is_on_post_method_with_endpoint() {
     logger.info("@Given User is on Post Method with endpoint");
     RestAssured.baseURI = properties.getProperty("base_uri");
		requestSpec = RestAssured.given();
		path = properties.getProperty("endpointPost");
	}

	@When("User sends request with valid inputs")
	public void user_sends_request_with_valid_inputs() throws Exception {
		logger.info("@When User sends request with valid inputs");
		requestSpecificationPost();
	}

	@Then("User should able to create new program with system generated program id")
	public void user_should_able_to_create_new_program_with_system_generated_program_id() {

	}

	@When("User sends the request with already available program name")
	public void user_sends_the_request_with_already_available_program_name() throws Exception {
		logger.info("@When User sends the request with already available program name");
		requestSpecificationPost();
	}

	@Then("User should receive status code and message for post")
	public void user_should_receive_status_code_and_message_for_post() throws IOException {
		logger.info("@Then User should receive status code and message for post");
		String expStatusCode = excelReaderUtil.getDataFromExcel(scenario.getName(), "StatusCode");
		String expMessage = excelReaderUtil.getDataFromExcel(scenario.getName(), "Message");
		logger.info("Expected response code: " + expStatusCode + " Expected message is: " + expMessage);
		String responseBody = response.prettyPrint();
		JsonPath js = response.jsonPath();
		System.out.println("New Program Created is :"+js.get("programId"));
		// Post Schema Validation
		//assertThat(responseBody, matchesJsonSchemaInClasspath("userResponse_schema.json"));
				
		//Status code validation
		assertEquals(Integer.parseInt(expStatusCode), response.statusCode());
		
		//Message validation
		//response.then().assertThat().extract().asString().contains("User successfully Created!");
		logger.info("Response Status code is =>  " + response.statusCode());
		logger.info("Response Body is =>  " + responseBody);
	}

	@When("User sends the request with blank fields")
	public void user_sends_the_request_with_blank_fields() throws Exception {
		logger.info("@When User sends the request with blank fields");
		requestSpecificationPost();
	}
}
