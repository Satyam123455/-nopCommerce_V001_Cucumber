    
@tag
Feature: Login

   @sanity @regression
  Scenario: Successful Login with valid credentials
    Given User Launch Chrome Browser
    When User Opens URL "https://admin-demo.nopcommerce.com/login"
    And User Enters Email as "admin@yourstore.com" and Password as "admin"
    And Click on Login
    Then Page Title should be "Dashboard / nopCommerce administration"
    When User click on Logout link
    Then Page Title should be "Your store. Login"
    And Close the browser
    
    
    @regression
  Scenario Outline: Login Data Driven
    Given User Launch Chrome Browser
    When User Opens URL "https://admin-demo.nopcommerce.com/login"
    And User Enters Email as "<email>" and Password as "<password>"
    And Click on Login
    Then Page Title should be "Dashboard / nopCommerce administration"
    When User click on Logout link
    Then Page Title should be "Your store. Login"
    And Close the browser
    
    Examples:
          | email | password |
          | admin@yourstore.com | admin |
          | admin1@yourstore.com | admin123 |	

