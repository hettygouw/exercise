package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pageObject.ValuesPage;
import utilities.BrowserUtils;

/**
 * @author Hetty Gouw
 *
 */
public class StepDefinition {
	WebDriver driver;
	ValuesPage valuesPage;
	
	@Before
	public void setUp() {
		this.driver = BrowserUtils.getDriver("chrome");
	}
	
	@After
	public void tearDown(Scenario scenario) {
		if(scenario.isFailed()) {
			BrowserUtils.takesScreenshot();
		}
		BrowserUtils.closeApplication();
	}
	
	@Given("^User opens Values page$")
	public void user_open_values_page() throws Exception{
		BrowserUtils.navigateTo("https://www.exercise1.com/values");
		valuesPage = new ValuesPage(driver);
	}
	
	@Then("^User verifies the number of values appear on the screen$")
	public void user_verifies_the_number_of_values_appear_on_the_screen() throws Exception{
		Assert.assertTrue("Verify number of values is correct", valuesPage.verifyNumberOfValuesIsCorrect());
	}
	
	@Then("^User verifies the values on the screen are greater than 0$")
	public void user_verifies_the_values_on_the_screen_are_greater_than_0() throws Exception{
		Assert.assertTrue("Verify values are greater than 0", valuesPage.verifyTheValuesAreGreaterThanZero());
	}

	@Then("^User verifies the values are formatted as currencies$")
	public void user_verifies_the_values_are_formatted_as_currencies() throws Exception{
		Assert.assertTrue("Verify the values are formatted in currencies", valuesPage.verifyTheValuesAreFormattedInCurrencies());
	}
	
	@Then("^User verifies the total balance is correct based on the values listed on the screen$")
	public void user_verifies_the_total_balance_is_correct_based_on_the_values_listed_on_the_screen() throws Exception{
		Assert.assertTrue("Verify total balance is correct", valuesPage.verifyTheTotalBalanceIsCorrect());
	}
	
	@Then("^User verifies the total balance matches the sum of the values$")
	public void user_verifies_the_total_balance_matches_the_sum_of_the_values() throws Exception{
		//same verification as above
		Assert.assertTrue("Verify total balance is correct", valuesPage.verifyTheTotalBalanceIsCorrect());
	}
}
