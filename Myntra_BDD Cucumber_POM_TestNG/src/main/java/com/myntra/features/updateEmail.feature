Feature: Update Email Adress 
	As a User, I want to update my Email Address Securely.

Background: 

	Given Browser is opened 
	
	When I enter URL 
	Then LOG IN page should be loaded 

	When I LOG IN with valid credentials
	Then Home Page should be loaded 
	
@UpdateEmail 
Scenario: 
	To validate that I able to Update Emaid Id securely or not.

	When I Place cursor on Profile icon 
	And I click on Edit profile 
	Then Profile page should be loaded
	And Email Id in Email field should be same with Log IN credentials
	
	When I enter a new Email Id and Click on Save details 
	Then portal should ask to check verification Email 
	But details are saved without verification 
