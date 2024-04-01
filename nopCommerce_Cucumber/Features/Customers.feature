Feature: Customers

  Background: User Logged In
       Given User Launch Chrome Browser
       When User Opens URL "https://admin-demo.nopcommerce.com/login"
       And User Enters Email as "admin@yourstore.com" and Password as "admin"
       And Click on Login
       Then User can view Dashboard page
 
     @sanity
	Scenario: Add a New Customer
	     When User click on Customers Menu
       And Click on customers Menu Item
       And Click on Add new button
       Then User can view Add new Customer Page
       When User enter customer Info
       And Click on Save button
       Then User can view Confirmation message "The new customer has been added successfully."
       And Close the browser
       
      @regression 
  Scenario: Search Customer by using Email id
       When User click on Customers Menu
       And Click on customers Menu Item
       And Enter Customer "Email" as a "arthur_holmes@nopCommerce.com"
       When Click on Search button
       Then User Should found "arthur_holmes@nopCommerce.com" in the Search Table
       And Close the browser
       
      @regression
  Scenario: Search Customer by using First Name and Last Name
       When User click on Customers Menu
       And Click on customers Menu Item
       And Enter Customer "First name" as a "Rajesh"
       And Enter Customer "Last name" as a "Nani"
       When Click on Search button
       Then User Should found "Rajesh Nani" in the Search Table
       And Close the browser  
					
