package stepDefinations;

import java.io.IOException;

import Util.TestBase;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class searchSteps extends TestBase {

	@Given("^I want to access KSRTC app$")
	public void i_want_to_access_ksrtc_app() {
		searchPage.getKSRTURL();
	}

	@When("^user must be logged into ksrtc app with valid credentials \"([^\"]*)\" and rownumber \"([^\"]*)\"$")
	public void user_must_be_logged_into_ksrtc_app_with_valid_credentials_and_rownumber(String sheetName, int rowNumber)
			throws IOException, InterruptedException {
		searchPage.logintoApplication(sheetName, rowNumber);
	}

	@And("^user enters \"([^\"]*)\" leaving from details$")
	public void user_enters_leaving_from_details(String sourcePlace) throws InterruptedException, IOException {
		searchPage.scrolltoLeavingFrom();
		searchPage.enterLeavingFrom(sourcePlace);
	}

	@And("^select leaving from city from drop list$")
	public void select_leaving_from_city_from_drop_list() {
		searchPage.selectLeavingPlaceFromDropList();
	}

	@And("^user enters \"([^\"]*)\" going to details$")
	public void user_enters_going_to_details(String destPlace) throws InterruptedException, IOException {
		searchPage.enterGoingTo(destPlace);
	}

	@And("^select going to city from drop list$")
	public void select_going_to_city_from_drop_list() {
		searchPage.selectGoingPlaceFromDropList();
	}

	@And("^user enters departure date \"([^\"]*)\"$")
	public void user_enters_departure_date(String date) throws InterruptedException, IOException {
		searchPage.selectDepartureDate(date);
	}

	@And("^user enters return date \"([^\"]*)\"$")
	public void user_enters_return_date(String date) throws InterruptedException, IOException {
		searchPage.selectReturnDate(date);
	}

	@And("^user clicks on search for bus button$")
	public void user_clicks_on_search_for_bus_button() throws InterruptedException, IOException {
		searchPage.clickOnSearchButton();
	}

	@Then("^user should verify for source to destination details both ways in service page$")
	public void user_should_verify_for_source_to_destination_details_both_ways_in_service_page() {
		searchPage.verifyElementPresent();
	}

}
