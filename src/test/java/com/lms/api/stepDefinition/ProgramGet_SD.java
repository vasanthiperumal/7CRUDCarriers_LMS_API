package com.lms.api.stepDefinition;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lms.api.utilities.ExcelReader;
import com.lms.api.utilities.ConfigReader;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ProgramGet_SD {
	RequestSpecification requestSpec;
	Response response;
	String path;
	String sheetGet;
	//String invStatusCode;
	//static String validId;

	ExcelReader excelReaderUtil;
	Scenario scenario;
	Properties properties;

	private static final Logger logger = LogManager.getLogger(ProgramGet_SD.class);

	public ProgramGet_SD() {
		ConfigReader config = new ConfigReader();
		properties = config.loadProperties();
	}

	@Before
	public void initializeDataTable(Scenario scenario) throws Exception {
		this.scenario = scenario;
		sheetGet = properties.getProperty("sheetGet");
		excelReaderUtil = new ExcelReader(properties.getProperty("programExcelpath"));
		excelReaderUtil.readSheet(sheetGet);
	}

	public void requestSpecification() {
		requestSpec.header("Accept", ContentType.JSON.getAcceptHeader()).contentType(ContentType.JSON);
		requestSpec.log().all();
		response = requestSpec.when().get(path);
	}

	@Given("User is on GET method with endpoint")
	public void user_is_on_get_method_with_endpoint() {
		logger.info("@Given User is on Get Method with end point");
		RestAssured.baseURI = properties.getProperty("base_uri");
		requestSpec = RestAssured.given();
		path = properties.getProperty("endpoint");
	}

	@When("User sends the request with valid input")
	public void user_sends_the_request_with_valid_input() {
		logger.info("@When User sends the request with valid inputs");
		requestSpecification();
	}

	@Then("User is able to read all Program details and receive status code for get")
	public void user_is_able_to_read_all_program_details_and_receive_status_code_for_get() throws IOException {
		logger.info("@Then User is able to read all user details and receive status code for get");
		String expStatusCode = excelReaderUtil.getDataFromExcel(scenario.getName(), "StatusCode");
		String responseBody = response.prettyPrint();
		assertEquals(Integer.parseInt(expStatusCode), response.statusCode());
		assertThat(responseBody, matchesJsonSchemaInClasspath("GetAllProgram.json"));
		logger.info("Response Status code is =>  " + response.statusCode());
	}

	@Given("User is on GET method with end point with single programId")
	public void user_is_on_get_method_with_end_point_with_single_program_id() throws IOException {
		logger.info("@Given User is on Get Method with end point for single user");
		RestAssured.baseURI = properties.getProperty("base_uri");

		requestSpec = RestAssured.given();
		String programId = excelReaderUtil.getDataFromExcel(scenario.getName(), "ProgramId");

		System.out.println("Program id from Excel is==>" + programId);
		// Converted the programId as int or decimal as it is passed only as a string
		// from excel
	/*	if (programId.equalsIgnoreCase("000")) {
			int invProgramId = Integer.parseInt(programId);
			path = properties.getProperty("endpointGet") + invProgramId;
		} else if (programId.equalsIgnoreCase("1.23")) {
			float invid = Float.parseFloat(programId);
			path = properties.getProperty("endpointGet") + invid;
		} else*/
			path = properties.getProperty("endpointGet") + programId;
		//validId = programId;
		logger.info("ProgramId from excel : " + programId);
	}

	@When("User sends the request with existing programId")
	public void user_sends_the_request_with_existing_program_id() {
		logger.info("@When User sends the request with existing programId");
		requestSpecification();
	}

	@Then("User should see the program details for the given programId")
	public void user_should_see_the_program_details_for_the_given_program_id() throws IOException {
		logger.info("@User should see the program details for the given programId");
		String expStatusCode = excelReaderUtil.getDataFromExcel(scenario.getName(), "StatusCode");
		String responseBody = response.prettyPrint();
		assertEquals(Integer.parseInt(expStatusCode), response.statusCode());
		logger.info("Actual Response Status code=>  " + response.statusCode() + "  Expected Response Status code=>  "
				+ expStatusCode);
		assertThat(responseBody, matchesJsonSchemaInClasspath("GetProgram.json"));
		logger.info("Schema vlaidator is sucess");
		logger.info("Response Status code is =>  " + response.statusCode());
		logger.info("Response Body is => " + responseBody);

		JsonPath js = response.jsonPath();
		String rsProgramId = js.get("programId");
		logger.info("programId from json :  " + rsProgramId);
	}
	@When("User sends the request with invalid programId")
	public void user_sends_the_request_with_invalid_program_id() {
		logger.info("@When User sends request with invalid userId");
		requestSpecification();
	}

	@Then("User should receive error status code and message")
	public void user_should_receive_error_status_code_and_message() throws IOException {
		logger.info("@Then User should receive error status code and message");
		String expStatusCode = excelReaderUtil.getDataFromExcel(scenario.getName(), "StatusCode");
		assertEquals(Integer.parseInt(expStatusCode), response.statusCode());
		logger.info("Response Status code is =>  " + response.statusCode());
	}

	@When("User sends request with alphanumeric programId")
	public void user_sends_request_with_alphanumeric_program_id() {
		logger.info("@When User sends request with alphanumeric programId");
		requestSpecification();
	}

	@When("User sends request with a null programId")
	public void user_sends_request_with_a_null_program_id() {
		logger.info("@When User sends request with a null programId");
		requestSpecification();
	}

	@When("User sends request with a decimal programId")
	public void user_sends_request_with_a_decimal_program_id() {
		logger.info("@When User sends request with a decimal programId");
		requestSpecification();
	}

	@Given("User is on Get Method with end point with programId as blank")
	public void user_is_on_get_method_with_end_point_with_program_id_as_blank() {
		logger.info("@Given User is on Get Method with end point with userID as blank");
		RestAssured.baseURI = properties.getProperty("base_uri");
		requestSpec = RestAssured.given();
		path = properties.getProperty("endpointGet");
	}

	@When("User sends request with a blank programId")
	public void user_sends_request_with_a_blank_program_id() {
		logger.info("@When User sends request with a blank programId");
		requestSpecification();
	}

	@Then("User should receive error status code for get")
	public void user_should_receive_error_status_code_for_get() throws IOException {
		logger.info("@Then User should receive error status code for get");
		String expStatusCode = excelReaderUtil.getDataFromExcel(scenario.getName(), "StatusCode");
		//invStatusCode = properties.getProperty("invStatusCode");
		String responseBody = response.prettyPrint();
		assertEquals(Integer.parseInt(expStatusCode), response.statusCode());
		logger.info("Response Status code is =>  " + response.statusCode());
		logger.info("Response Body is => " + responseBody);
	}
}
