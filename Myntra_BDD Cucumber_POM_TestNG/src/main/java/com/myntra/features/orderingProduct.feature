Feature: Ordering a product 
	As a User, I want to purchase a product using Phone Pe

Background: 
	Given Browser is opened 
	
	When I enter URL 
	Then LOG IN page should be loaded 
	
	When I LOG IN with valid credentials 
	Then Home Page should be loaded 
	
@OrderingProduct 
Scenario: 
	To validate that user should be able order a searched item of least price

	When I Search for a product and click on Search Button 
	Then Products page should be loaded
	
	When I set Sort By: price - low to high 
	Then products should be displayed in ascending order of the prices 
	
	When I add the first product to Wishlist 
	And Click on Wishlist button in navigation bar 
	Then added product should be in wishlist 
	
	When I click on Move to Bag and select size 
	Then product should be moved to Bag 
	
	When I click on Bag in Navigation Bar 
	Then Shopping Bag Page should be open 
	
	When I click on Place order 
	Then Address Page should be open 
	
	When I click on Continue 
	Then Payment Page should be open 
	
	When I choose Phone pe and Click on Pay Now 
	Then Phone pe payments page should be open 
