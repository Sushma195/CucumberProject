package stepDefinations;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import Util.TestBase;
import Util.TestUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class MasterHook extends TestBase {

	@Before
	public void setup() {
		driver = getDriver();
	}

	@After
	public void getScreenShotAttachedAfterEachScenario(Scenario scenario) throws IOException, InterruptedException {
		try {
			if (driver != null && scenario.isFailed()) {
				// ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL,
				// MediaEntityBuilder.createScreenCaptureFromBase64String(TestUtil.captureScreenShot()).build());

				// scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES),
				// "image/png",
				// scenario.getName());

//				final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//				scenario.attach(screenshot, "image/png", scenario.getName());
				
				TestUtil.captureScreenShot(scenario);

				driver.manage().deleteAllCookies();
				driver.quit();
				driver = null;
			}
			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
				driver = null;
			}
		} catch (Exception e) {

		}

	}
}
