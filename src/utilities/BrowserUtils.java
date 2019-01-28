package utilities;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import pageObject.ValuesPage;

/**
 * @author Hetty Gouw
 *
 */
public class BrowserUtils {
	public static WebDriver driver;
	static Logger log = Logger.getLogger(ValuesPage.class);
	
	public static WebDriver getDriver(String browser) {
		System.setProperty("webdriver.chrome.driver","C:\\Selenium_Cucumber\\chrome_driver\\chromedriver.exe");
            
        driver = null;
        switch (browser) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                driver = new ChromeDriver();
        }
        return driver;
    }
	
	public static void maximizeBrowser() {
		driver.manage().window().maximize();
		implicitWait(10);
	}
	
	public static void implicitWait(int timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}
	
	public static void takesScreenshot() {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File("C:\\Users\\hetty\\eclipse-workspace\\HettyTest\\src\\screenshot.png"));
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
	
	public static void navigateTo(String URL) {
		driver.get(URL);
	}
	
	public static void closeApplication() {
		if(driver != null) {
			driver.close();
			driver.quit();
		}
	}
}
