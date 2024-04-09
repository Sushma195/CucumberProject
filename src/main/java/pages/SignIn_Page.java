package pages;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import Util.TestUtil;

public class SignIn_Page extends TestUtil {

	public static @FindBy(xpath = "//a[contains(text(),' Sign In')]") WebElement button_SignIn;
	public static @FindBy(name = "userName") WebElement textField_UserName;
	public static @FindBy(name = "password") WebElement textField_Password;
	public static @FindBy(css = "input#TermsConditions+label") String checkbox_TermsAndCondition;
	public static @FindBy(xpath = "//input[@id='submitBtn']") WebElement button_Login;
	public static @FindBy(xpath = "//a[text()='Booking']") WebElement tab_booking;
	public static @FindBy(xpath = "//div[contains(text(),'Welcome')]") WebElement welcomePage;
	public @FindBy(xpath = "//a[text()='Logout']") WebElement button_logout;
	public @FindBy(xpath = "//*[contains(text(),'Login incorrect. Please try again')])") WebElement errorPage;

	public static String username;
	public static String password;

	public SignIn_Page() {
		super();
	}

	public SignIn_Page getKSRTCURL() throws IOException {
		getDriver().get(browserUrl);
		// getUrl(browserUrl);
		return new SignIn_Page();
	}

	public SignIn_Page clickOnSignInButton() throws IOException, InterruptedException {
		waitAndClickElement(button_SignIn);
		return new SignIn_Page();
	}

	public SignIn_Page enterUserName(String userName) throws IOException {
		sendKeysToElement(textField_UserName, userName);
		return new SignIn_Page();
	}

	public SignIn_Page enterPassword(String password) throws IOException {
		sendKeysToElement(textField_Password, password);
		return new SignIn_Page();
	}

	public SignIn_Page checkBoxTermsAndCondition() throws IOException {
		WebElement checkBox = driver.findElement(By.cssSelector("input#TermsConditions+label"));
		waitAndclickElementUsingJS(checkBox);
		return new SignIn_Page();
	}

	public SignIn_Page clickOnLoginButton() throws IOException, InterruptedException {
		waitAndclickElementUsingJS(button_Login);
		return new SignIn_Page();
	}

	public SignIn_Page verifyPageAfterClickingOnLogin(String message) throws IOException, InterruptedException {

		try {

			if (driver.findElement(By.xpath("//*[contains(text(),'Login incorrect. Please try again')]"))
					.isDisplayed()) {
				try {
					Assert.assertEquals(
							driver.findElement(By.xpath("//*[contains(text(),'Login incorrect. Please try again')]"))
									.getText().replaceAll("\\s", ""),
							message);
				} catch (Exception e) {

				}
			}
		} catch (Exception e) {

		}

		try {
			if (driver.findElement(By.xpath("//div[contains(text(),'Welcome')]")).isDisplayed()) {
				try {
					String str = driver.findElement(By.xpath("//div[contains(text(),'Welcome')]")).getText()
							.replaceAll("\\s", "");
					Assert.assertEquals(str, message);
				} catch (Exception e) {

				} finally {
					waitAndClickElement(welcomePage);
					waitAndclickElementUsingJS(button_logout);
				}
			}
		} catch (Exception e) {
		}

		return new SignIn_Page();

	}

	public void readFromExcel(String sheetName, Integer rowNumber) throws EncryptedDocumentException, IOException {

		List<Map<String, String>> testdata = getData(excelFilePath, sheetName);
		username = testdata.get(rowNumber).get("username");
		password = testdata.get(rowNumber).get("password");

		if (username != null && password != null) {
			signPage.enterUserName(username);
			signPage.enterPassword(password);
		}

	}
}
