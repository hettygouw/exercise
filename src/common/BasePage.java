package common;

import org.openqa.selenium.WebDriver;

/**
 * @author Hetty Gouw
 *
 */
public class BasePage {
	protected WebDriver driver;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
}
