package stepDefinations;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;

import Util.TestBase;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class signInSteps extends TestBase {

	@Given("^I want to access ksrtc app$")
	public void i_want_to_access_ksrtc_app() throws IOException, InterruptedException {
		signPage.getKSRTCURL();
	}

	@When("^I click on signin button$")
	public void i_click_on_signin_button() throws IOException, InterruptedException {
		signPage.clickOnSignInButton();
	}

	@And("^user enters sheetname \"([^\"]*)\" and rownumber \"([^\"]*)\"$")
	public void user_enters_sheetname_and_rownumber(String sheetName, Integer rowNumber)
			throws EncryptedDocumentException, IOException {
		signPage.readFromExcel(sheetName, rowNumber);
	}

	@And("^I ckeckbox terms and condition$")
	public void i_ckeckbox_terms_and_condition() throws IOException {
		signPage.checkBoxTermsAndCondition();
	}

	@And("^I click on login button$")
	public void i_click_on_login_button() throws IOException, InterruptedException {
		Thread.sleep(5000);
		signPage.clickOnLoginButton();
	}

	@Then("^I should be navigated to welcom page and verify the page \"([^\"]*)\"$")
	public void i_should_be_navigated_to_welcom_page_and_verify_the_page(String message)
			throws IOException, InterruptedException {
		signPage.verifyPageAfterClickingOnLogin(message);
	}

}
