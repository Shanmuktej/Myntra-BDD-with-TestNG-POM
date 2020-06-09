Feature: Items Count 
As a Test Engineer, I want to check whether Items Count works properly or not.

Scenario: 
	To check whether Items counts shown is accurate or not

	Given Browser is opened 
	When I enter Home Page URL "https://www.myntra.com" 
	Then Home Page should be loaded
	
	When I Search for a product and click on Search Button 
	Then Products page should be loaded
	
	When I count all items from search results 
	And validate with Items count displayed
	Then Both Items count should be same
	