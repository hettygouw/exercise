package pageObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import common.BasePage;
import utilities.WaitUtils;

/**
 * @author Hetty Gouw
 *
 */
public class ValuesPage extends BasePage{

	WebDriver driver;
	static Logger log = Logger.getLogger(ValuesPage.class);
	private static final String TOTAL_BALANCE = "lbl_ttl_val";
	private List<String> labelValueStrList = new ArrayList<String>();
	private List<String> textValueStrList = new ArrayList<String>();
	private List<BigDecimal> textValueBigDecList = new ArrayList<BigDecimal>();
	
	public ValuesPage(WebDriver driver) throws Exception{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);		
		WaitUtils.waitUntilVisibilityOfElement(driver, By.id(TOTAL_BALANCE));
		populateLabelValue();
		populateTextValue();
	}
	
	public void populateLabelValue() throws Exception{
		List<WebElement> labelValueElems = driver.findElements(By.cssSelector("[id^='lbl_val_']"));
		String text = "";
		for(int i=0; i<labelValueElems.size(); i++) {
			text = labelValueElems.get(i).getText();
			labelValueStrList.add(text);
		}
	}
	
	public void populateTextValue() throws Exception{
		List<WebElement> textValueElems = driver.findElements(By.cssSelector("[id^='txt_val_']"));
		String text = "";
		BigDecimal bigDec = new BigDecimal("");
		for(int i=0; i<textValueElems.size(); i++) {
			text = textValueElems.get(i).getText();
			textValueStrList.add(text);
			bigDec = new BigDecimal(text.replaceAll("[$,]", ""));
			textValueBigDecList.add(bigDec);
		}
	}
	
	public BigDecimal getTotalBalance() throws Exception{
		WebElement elem = driver.findElement(By.id("txt_total_val"));
		String text = elem.getText();
		return new BigDecimal(text.replaceAll("[$,]", ""));
	}
	
	public BigDecimal getTextValueTotal() throws Exception{
		BigDecimal total = BigDecimal.ZERO;
		for(int i=0; i<textValueBigDecList.size(); i++) {
			total = total.add(textValueBigDecList.get(i));
		}
		return total;
	}
	
	public boolean verifyNumberOfValuesIsCorrect() throws Exception{
		return labelValueStrList.size() == textValueStrList.size();
	}
	
	public boolean verifyTheValuesAreGreaterThanZero() throws Exception{
		boolean result = true;
		for (int i = 0; i < textValueBigDecList.size(); i++) {
			BigDecimal value = textValueBigDecList.get(i);
			if (value.compareTo(BigDecimal.ZERO) != 1) {
				log.debug("text value that is not greater than 0: " + value);
				result = false;
			}
		}
		return result;
	}
	
	public boolean verifyTheTotalBalanceIsCorrect() throws Exception{
		if(getTotalBalance().compareTo(getTextValueTotal()) == 0) {
			return true;
		}
		return false;
	}
	
	public boolean verifyTheValuesAreFormattedInCurrencies() throws Exception{
		String text = "";
		boolean isMatching = true;
		for(int i=0; i<textValueStrList.size(); i++) {
			text = textValueStrList.get(i);
			isMatching = text.matches("^\\$(\\d{1,3}(\\,\\d{3})*|(\\d+))(\\.\\d{2})?$");
			if(!isMatching) {
				log.debug("The value that is not formatted in Currency: " + text);
				isMatching = false;
			}
		}
		return isMatching;
	}
}
