package pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import Util.TestUtil;

public class Search_Page extends TestUtil {

	public @FindBy(xpath = "//input[@id= 'fromPlaceName']") WebElement textfield_LeavingFrom;
	public @FindBy(xpath = "//input[@id='toPlaceName']") WebElement textfield_GoingTo;
	public @FindBy(xpath = "//input[@id='txtJourneyDate']") WebElement depatureDate;
	public @FindBy(xpath = "//input[@id='txtReturnJourneyDate']") WebElement returnDate;
	public @FindBy(xpath = "//button[contains(text(),'Search for Bus')]") WebElement button_Search;

	public @FindBy(xpath = "(//ul[1]//li//a[@tabindex='-1'])[1]") WebElement dropdown_getLeavingPalce;
	public @FindBy(xpath = "(//ul[2]//li//a[@tabindex='-1'])[1]") WebElement dropdown_getGoingPalce;

	public @FindBy(xpath = "//input[@id='txtJourneyDate']") WebElement textfield_departureDate;
	public @FindBy(xpath = "//input[@id='txtReturnJourneyDate']") WebElement textfield_returnDate;

	public @FindBy(xpath = "//div[@id='ui-datepicker-div']//following::span[text()='Next']") WebElement span_Next;
	public @FindBy(xpath = "//span[@class='ui-datepicker-month']") WebElement span_Month;
	public @FindBy(xpath = "//span[@class='ui-datepicker-year']") WebElement span_Year;
	public @FindBy(xpath = "//td[@data-handler='selectDay']/a") By table_ListOfAvailableDays;
	public @FindBy(xpath = "//div[contains(@class,'one-way-search')]") WebElement class_oneWaySearch;
	public @FindBy(xpath = "//div[contains(@class,'two-way-search')]") WebElement class_twoWaySearch;

	public Search_Page() {
		super();
	}

	public Search_Page getKSRTURL() {
		getDriver().get(browserUrl);
		// getUrl(browserUrl);
		return new Search_Page();
	}

	public Search_Page logintoApplication(String sheetName, Integer rowNumber)
			throws IOException, InterruptedException {
		SignIn_Page signInPage = new SignIn_Page();
		signInPage.clickOnSignInButton();
		signInPage.readFromExcel(sheetName, rowNumber);
		signInPage.checkBoxTermsAndCondition();
		signInPage.clickOnLoginButton();

		return new Search_Page();
	}

	public Search_Page scrolltoLeavingFrom() {
		scrollToElementByWebElementLocator(textfield_LeavingFrom);
		return new Search_Page();
	}

	public Search_Page enterLeavingFrom(String sourcePlace) throws InterruptedException, IOException {
		waitAndClickElement(textfield_LeavingFrom);
		sendKeysToElement(textfield_LeavingFrom, sourcePlace);
		return new Search_Page();
	}

	public Search_Page enterGoingTo(String destPlace) throws InterruptedException, IOException {
		waitAndClickElement(textfield_GoingTo);
		sendKeysToElement(textfield_GoingTo, destPlace);
		return new Search_Page();
	}

	public Search_Page selectLeavingPlaceFromDropList() {
		actionMoveAndClick(dropdown_getLeavingPalce);
		return new Search_Page();
	}

	public Search_Page selectGoingPlaceFromDropList() {
		actionMoveAndClick(dropdown_getGoingPalce);
		return new Search_Page();
	}

	public Search_Page selectDepartureDate(String date) throws InterruptedException, IOException {
		Thread.sleep(3000);
		selectDate(date, textfield_departureDate);
		return new Search_Page();
	}

	public Search_Page selectReturnDate(String date) throws InterruptedException, IOException {
		Thread.sleep(3000);
		selectDate(date, textfield_returnDate);
		return new Search_Page();
	}

	public Search_Page clickOnSearchButton() throws InterruptedException, IOException {
		waitAndClickElement(button_Search);
		return new Search_Page();
	}

	public Search_Page verifyElementPresent() {
		try {
			if (isElementDisplayed(class_oneWaySearch) && isElementDisplayed(class_twoWaySearch)) {
				return new Search_Page();
			}
		} catch (Exception e) {
			Assert.fail("Search bus result not found");
		}
		return null;
	}

	public void selectDate(String dateFormat, WebElement calenderEle) throws InterruptedException, IOException {

		String splitDateFormat[] = splitMonthDateYear(dateFormat);
		String date = splitDateFormat[0];
		String month = splitDateFormat[1];
		String year = splitDateFormat[2];

		waitAndClickElement(calenderEle);

		while (true) {
			String getYear = getTextOfElement(span_Year);// driver.findElement(By.xpath(span_Year)).getText();
			String getMonth = getTextOfElement(span_Month);// driver.findElement(By.xpath(span_Month)).getText();

			if (year.equals(getYear) && month.equalsIgnoreCase(getMonth)) {
				break;
			} else {
				actionMoveAndClick(span_Next);
			}
		}

		// date selection
		// Available dates for current month and year which are enabled (means present
		// and future date)
		List<WebElement> listOfDays = driver.findElements(By.xpath("//td[@data-handler='selectDay']/a"));

		for (int i = 0; i < listOfDays.size(); i++) {
			if (listOfDays.get(i).getText().equals(date)) {
				listOfDays.get(i).click();
				break;
			}
		}

	}

	private String[] splitMonthDateYear(String date) {
		String arr[] = date.split("-");
		return arr;
	}

}
