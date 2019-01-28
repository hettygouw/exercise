package utilities;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Hetty Gouw
 *
 */
public class WaitUtils {
	private static final long DEFAULT_TIMEOUT = 10;
	static Logger log = Logger.getLogger(WaitUtils.class);
	
	public static void waitUntilVisibilityOfElement(WebDriver driver, By locator) {
		try {
			new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(locator));
		}
		catch(Exception e) {
			log.error(e.getMessage());
			BrowserUtils.closeApplication();
		}
	}
}
