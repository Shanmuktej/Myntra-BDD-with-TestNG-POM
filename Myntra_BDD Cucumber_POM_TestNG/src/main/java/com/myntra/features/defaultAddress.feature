Feature: Default Address
  As a User, I want to add a default addrees or make an added Address as default.

  Background: 
    Given Browser is opened

  @AddDefaultAddress
  Scenario Outline: 
    To validate whether I am able to add a default address or not

    When I enter URL
    Then LOG IN page should be loaded
  
    When I LOG IN with valid credentials
    Then Home Page should be loaded
  
    When I Place cursor on Profile icon
     And I click on Saved Addresses
    Then Address page should be loaded
  
    When I click on Add Address button
     And Enter <Name>, <Mobile>, <Pincode>, <Address> details
     And Select Locality, Type of Address
     And Click on Make this as my default address checkBox and Click on Save
    Then Added <Name> address should be displayed as default address

    Examples: 
      | Name       | Mobile       | Pincode  | Address                                            |
      | "Shanmuka" | "9059942151" | "530008" | "House No -23, Main Building, Happy Street, Vizag" |

  @MakeThisDefaultAddress
  Scenario: 
    To validate whether I am able to make an Address as Default Address or not

    When I click on Make this default button of Other Address
    Then the Other Address should be made as Default Address
    
