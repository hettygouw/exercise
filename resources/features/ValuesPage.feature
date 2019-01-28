Feature: Values page
This feature verifies the values on Values page
 
Scenario: Verify the values on the page
Given User opens Values page
Then User verifies the number of values appear on the screen
And User verifies the values on the screen are greater than 0
And User verifies the values are formatted as currencies

Scenario: Verify the total balance on the page
Given User opens Values page
Then User verifies the total balance is correct based on the values listed on the screen
And User verifies the total balance matches the sum of the values