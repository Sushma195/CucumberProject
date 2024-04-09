package Util;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import pages.Search_Page;
import pages.SignIn_Page;

public class TestBase {
	public static WebDriver driver;
	public static SignIn_Page signPage;
	public static Search_Page searchPage;
	public static String browserUrl;
	public static String excelFilePath;

	public WebDriver getDriver() {
		try {
			// Read Config
			Properties p = new Properties();
			FileInputStream fi = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\config\\config.properties");
			p.load(fi);
			String browserName = p.getProperty("browser");
			browserUrl = p.getProperty("ksrtcurl");
			excelFilePath = p.getProperty("excelfilepath");

			switch (browserName) {

//			case "firefox":
//				if (null == driver) {
//					System.setProperty("webdriver.gecko.driver", "");
//					DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//					capabilities.setCapability("marionette", true);
//					driver = new FirefoxDriver();
//				}
//				break;

			case "chrome":
				if (null == driver) {
					System.setProperty("webdriver.chrome.driver",
							System.getProperty("user.dir") + "\\src\\test\\java\\drivers\\chromedriver.exe");
					driver = new ChromeDriver();
					driver.manage().window().maximize();
					Thread.sleep(3000);
				}
				break;

//			case "ie":
//				// code
//				if (null == driver) {
//					DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
//					System.setProperty("webdriver.ie.driver", "");
//					capabilities.setCapability("ignoreZoomSetting", true);
//					driver = new InternetExplorerDriver(capabilities);
//					driver.manage().window().maximize();
//				}
//				break;
			}
		} catch (Exception e) {
			System.out.println("Unable to load browser: " + e.getMessage());
		} finally {
			signPage = PageFactory.initElements(driver, SignIn_Page.class);
			searchPage = PageFactory.initElements(driver, Search_Page.class);
		}
		return driver;
	}
}
