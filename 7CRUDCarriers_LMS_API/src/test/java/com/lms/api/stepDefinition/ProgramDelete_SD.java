package com.lms.api.stepDefinition;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Properties;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lms.api.utilities.ConfigReader;
import com.lms.api.utilities.ExcelReader;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ProgramDelete_SD {

	RequestSpecification requestSpec;
	Response response;
	String programId;
	String programName;
	String path;
	String sheetDelete;
	String errorCode;

	ExcelReader excelReaderUtil;
	Scenario scenario;
	Properties properties;

	private static final Logger logger = LogManager.getLogger(ProgramDelete_SD.class);

	public ProgramDelete_SD()
	{
		ConfigReader config = new ConfigReader();
		properties = config.loadProperties();
	}

	@Before
	public void initializeDataTable(Scenario scenario) throws Exception {
		this.scenario = scenario;
		sheetDelete = properties.getProperty("sheetDelete");
		excelReaderUtil=new ExcelReader(properties.getProperty("programExcelpath"));
		excelReaderUtil.readSheet(sheetDelete);
	}

	public void requestSpecificationDelete() {
		requestSpec.header("Accept", ContentType.JSON.getAcceptHeader()).contentType(ContentType.JSON);
		requestSpec.log().all();
		response = requestSpec.when().delete(path);
	}




	@Given("User is on DELETE method with endpoint")
	public void user_is_on_delete_method_with_endpoint() throws IOException {

		logger.info("@Given User is on DELETE method with endpoint");
		RestAssured.baseURI=properties.getProperty("base_uri");
		requestSpec = RestAssured.given();
		String programId = excelReaderUtil.getDataFromExcel(scenario.getName(), "ProgramId");
		System.out.println("Program id from Excel is==>" + programId);
		path = properties.getProperty("endpointDeleteId") + programId;
		logger.info("ProgramId from excel : " + programId);
	}

	@When("User sends request with existing programId  as input")
	public void user_sends_request_with_existing_program_id_as_input() {
		requestSpecificationDelete();
	}

	@When("User sends request with nonexisting programId  as input")
	public void user_sends_request_with_nonexisting_program_id_as_input() {
		requestSpecificationDelete();
	}

	@Then("user receive status code and message for delete")
	public void user_receive_status_code_and_message_for_delete() throws IOException {
		logger.info("@Then User should receive status code and message for delete");

		String expStatusCode = excelReaderUtil.getDataFromExcel(scenario.getName(), "StatusCode");
		assertEquals(Integer.parseInt(expStatusCode), response.statusCode());
		logger.info("Actual Response Status code=>  " + response.statusCode() + "  Expected Response Status code=>  "
				+ expStatusCode);

	}


	@Given("User is on DELETE method with endpoint by progname")
	public void user_is_on_delete_method_with_endpoint_by_progname() throws IOException {
		logger.info("@Given User is on DELETE method with endpoint by progname");
		RestAssured.baseURI=properties.getProperty("base_uri");
		requestSpec = RestAssured.given();
		String programName = excelReaderUtil.getDataFromExcel(scenario.getName(), "ProgramId");
		System.out.println("Program id from Excel is==>" + programName);
		path = properties.getProperty("endpointDeleteName") + programName;
		logger.info("ProgramId from excel : " + programName);
	}

	@When("User sends request with existing programName  as input")
	public void user_sends_request_with_existing_program_name_as_input() {
		requestSpecificationDelete();
	}

	@When("User sends request with nonexisting programName  as input")
	public void user_sends_request_with_nonexisting_program_name_as_input() {
		requestSpecificationDelete();
	}

	@Then("user receive status code for existing prog name")
	public void user_receive_status_code_for_existing_prog_name() throws IOException {
		logger.info("@Then user receive status code for existing prog name");
		String expStatusCode = excelReaderUtil.getDataFromExcel(scenario.getName(), "StatusCode");
		assertEquals(Integer.parseInt(expStatusCode), response.statusCode());
		logger.info("Actual Response Status code=>  " + response.statusCode() + "  Expected Response Status code=>  "
				+ expStatusCode);
	}



	@When("User sends request without passing programId or programName")
	public void user_sends_request_without_passing_program_id_or_program_name() {
		requestSpecificationDelete();
	}

	@Then("user receive status code for blank value")
	public void user_receive_status_code_for_blank_value() {
		errorCode = properties.getProperty("nfStatusCode");
		assertEquals(Integer.parseInt(errorCode), response.statusCode());
	}
}
