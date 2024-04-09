package Util;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.Scenario;

//import com.cucumber.listener.Reporter;

public class TestUtil extends TestBase {

	private static String screenshotName;

	static WebDriverWait wait;
	static JavascriptExecutor jsExecutor;
	public static int totalRow;

	public TestUtil() {
		// System.out.println();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		jsExecutor = (JavascriptExecutor) driver;
	}

	public void getUrl(String url) {
		getDriver().get(url);
	}

	public void waitAndClickElement(WebElement element) throws InterruptedException, IOException {
		boolean clicked = false;
		int attempts = 0;
		while (!clicked && attempts < 30) {
			try {
				this.wait.until(ExpectedConditions.elementToBeClickable(element)).click();
				System.out.println("Successfully clicked on the WebElement: " + "<" + element.toString() + ">");
				clicked = true;
			} catch (Exception e) {
				System.out.println("Unable to wait and click on WebElement, Exception: " + e.getMessage());
				Assert.fail(
						"Unable to wait and click on the WebElement, using locator: " + "<" + element.toString() + ">");
			}
			attempts++;
		}
	}

	public void clickOnElement(WebElement element) throws InterruptedException, IOException {
		try {
			this.wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		} catch (Exception e) {
		}
	}

	public void waitAndClickElementByUsingLocator(By by) {
		boolean elementClicked = false;
		int attempts = 0;
		while (!elementClicked && attempts < 10) {
			try {
				this.wait.until(ExpectedConditions.elementToBeClickable(by)).click();
				elementClicked = true;
			} catch (Exception e) {
				System.out.println("Unable to wait and click on the element specified " + e.getMessage());
				Assert.fail("Unable to wait and click on the element specified : " + "<" + by.toString() + ">");
			}
			attempts++;
		}
	}

	public void waitAndclickElementUsingJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			js.executeScript("arguments[0].click();", element);
			System.out
					.println("Successfully JS clicked on the following WebElement: " + "<" + element.toString() + ">");
		} catch (StaleElementReferenceException elementUpdated) {
			WebElement staleElement = element;
			Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(staleElement)).isEnabled();
			if (elementPresent == true) {
				js.executeScript("arguments[0].click();", elementPresent);
				System.out.println("(Stale Exception) Successfully JS clicked on the following WebElement: " + "<"
						+ element.toString() + ">");
			}
		} catch (NoSuchElementException e) {
			System.out.println("Unable to JS click on the following WebElement: " + "<" + element.toString() + ">");
			Assert.fail("Unable to JS click on the WebElement, Exception: " + e.getMessage());
		}
	}

	public String getTextOfElement(WebElement element) throws InterruptedException, IOException {
		boolean clicked = false;
		int attempts = 0;
		String txt = null;
		while (!clicked && attempts < 30) {
			try {
				txt = this.wait.until(ExpectedConditions.elementToBeClickable(element)).getText();

				clicked = true;
			} catch (Exception e) {

			}
			attempts++;
		}
		return txt;
	}

	public List<WebElement> findListOfElement(By by) throws InterruptedException, IOException {
		List<WebElement> elements = null;
		try {
			elements = this.wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));

		} catch (Exception e) {

		}
		return elements;
	}

	public void sendKeysToElement(WebElement ele, String textValue) {
		try {
			this.wait.until(ExpectedConditions.elementToBeClickable(ele)).isDisplayed();
			// ele.isDisplayed();
			ele.clear();
			ele.sendKeys(textValue);
		} catch (Exception e) {
			System.out.println(
					"Unable to loacte the element " + "<" + ele + ">" + "and unable to send the value " + textValue);
			Assert.fail("Unable to send key and value " + e.getMessage());

		}

	}

	public boolean isElementDisplayed(WebElement ele) {
		boolean flag = false;
		try {
			ele.isDisplayed();
			flag = true;
		} catch (Exception e) {
			System.out.println("Specified element is not displayed " + "<" + ele + ">");
			Assert.fail("Element not displayed, Exception : " + e.getMessage());
		}
		return flag;
	}

	public void actionMoveAndClick(WebElement ele) {
		Actions action = new Actions(driver);
		try {
			this.wait.until(ExpectedConditions.elementToBeClickable(ele)).isEnabled();
			action.moveToElement(ele).click().build().perform();
		} catch (StaleElementReferenceException e) {
			WebElement elementToClick = ele;
			boolean elementPresent = this.wait.until(ExpectedConditions.elementToBeClickable(elementToClick))
					.isEnabled();
			if (elementPresent) {
				action.moveToElement(ele).click().build().perform();
			}
		} catch (Exception e) {
			System.out.println("Unable to move and click on the specified element " + "<" + ele + ">");
			Assert.fail("Unable to move and click on element. Exception : " + e.getMessage());
		}

	}

	public void scrollToElementByWebElementLocator(WebElement element) {
		try {
			this.wait.until(ExpectedConditions.visibilityOf(element)).isEnabled();
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -400)");
			System.out.println(
					"Succesfully scrolled to the WebElement, using locator: " + "<" + element.toString() + ">");
		} catch (Exception e) {
			System.out.println("Unable to scroll to the WebElement, using locator: " + "<" + element.toString() + ">");
			Assert.fail("Unable to scroll to the WebElement, Exception: " + e.getMessage());
		}
	}

	// ExcelReader
	public List<Map<String, String>> getData(String excelFilePath, String sheetName)
			throws EncryptedDocumentException, IOException {

		Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
		Sheet sheet = workbook.getSheet(sheetName);
		workbook.close();
		return readSheet(sheet);
	}

	private List<Map<String, String>> readSheet(Sheet sheet) {

		Row row;
		Cell cell;
		totalRow = sheet.getLastRowNum();

		List<Map<String, String>> excelRows = new ArrayList<Map<String, String>>();
		for (int currentRow = 1; currentRow <= totalRow; currentRow++) {
			row = sheet.getRow(currentRow);

			int totalColumn = row.getLastCellNum();
			LinkedHashMap<String, String> columnMapData = new LinkedHashMap<String, String>();
			for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {
				cell = row.getCell(currentColumn);
				String columnHeaderValue = sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn)
						.getStringCellValue();

				columnMapData.put(columnHeaderValue, cell.getStringCellValue());
			}
			excelRows.add(columnMapData);
		}

		return excelRows;
	}

	/***
	 * EXTENT REPORT
	 ****************************************************************/
	public static String returnDateStamp(String fileExtension) {
		Date d = new Date();
		String date = d.toString().replace(":", "_").replace(" ", "_") + fileExtension;
		return date;
	}

	public static void captureScreenshot() throws IOException, InterruptedException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		screenshotName = returnDateStamp(".jpg");

		FileUtils.copyFile(srcFile,
				new File(System.getProperty("user.dir") + "\\FaliedTestsScreenshots\\imgs\\" + screenshotName));
	}

	public static String returnScreenshotName() {
		return (System.getProperty("user.dir") + "\\FaliedTestsScreenshots\\imgs\\" + screenshotName).toString();
	}

	public static String captureScreenShotFromBase64() throws IOException {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	}

	public static void captureScreenShot(Scenario scenario) throws IOException, InterruptedException {
		final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshot, "image/png", scenario.getName());

		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		screenshotName = returnDateStamp(".jpg");

		FileUtils.copyFile(srcFile,
				new File(System.getProperty("user.dir") + "\\FaliedTestsScreenshots\\imgs\\" + screenshotName));
	}

}
