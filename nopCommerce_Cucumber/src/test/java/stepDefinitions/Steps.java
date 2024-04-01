package stepDefinitions;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;


public class Steps extends BaseClass{

	@Before
	public void setup() throws IOException
	{
		prop = new Properties();
		FileInputStream configFile = new FileInputStream("./config.properties");
		prop.load(configFile);
		logger = Logger.getLogger("nopCommerce"); //Added Logger
		PropertyConfigurator.configure("log4j.properties");
		
		String br = prop.getProperty("browser");
		
		if(br.equals("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(br.equals("firefox"))
		{
			driver = new FirefoxDriver();
		}
		
		else if(br.equals("edge"))
		{
			driver = new EdgeDriver();
		}
		
		logger.info("**************** Launching Browser *********************");
		
	}

	
	@Given("User Launch Chrome Browser")
	public void user_launch_chrome_browser() {
		
		lp = new LoginPage(driver);

	}

	@When("User Opens URL {string}")
	public void user_opens_url(String url) {

	    driver.get(url);
	    driver.manage().window().maximize();
	}

	@When("User Enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {

		logger.info("***************** Providing Credentials **********************");
		lp.setUsername(email);
		lp.setPassword(password);
	}

	@When("Click on Login")
	public void click_on_login() throws InterruptedException {
		Thread.sleep(2000);
		logger.info("***************** Started Login **********************");
		lp.clickLogin();
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String title) {

		if(driver.getPageSource().contains("Login was unsuccessful."))
		{
			driver.close();
			logger.info("***************** Login Failed **********************");
			Assert.assertTrue(false);
		}
		else {
			Assert.assertEquals(title, driver.getTitle());
			logger.info("***************** Login Passed **********************");
			
		}
	}

	@When("User click on Logout link")
	public void user_click_on_logout_link() throws InterruptedException {
		 Thread.sleep(2000);
		 lp.clickLogout();
		 logger.info("***************** logging Out **********************");
		 Thread.sleep(2000);
	}

	@Then("Close the browser")
	public void close_the_browser() {
		logger.info("***************** Closing Browser **********************");
	    driver.quit();
	}
	
	// Customers Features Step Definitions
	
	
	@Then("User can view Dashboard page")
	public void user_can_view_dashboard_page() {
		
		addCust = new AddCustomerPage(driver);
		Assert.assertEquals(addCust.getPageTitle(), "Dashboard / nopCommerce administration");
		logger.info("***************** Viewed Dashboard Page **********************");
	}
	
	@When("User click on Customers Menu")
	public void user_click_on_customers_menu() throws InterruptedException {
		Thread.sleep(3000);
		addCust.clickOnCustomersMenu(); 
	}
	
	@When("Click on customers Menu Item")
	public void click_on_customers_menu_item() throws InterruptedException {
		Thread.sleep(2000);
		addCust.clickOnCustomersMenuItem();
	}
	
	@When("Click on Add new button")
	public void click_on_add_new_button() throws InterruptedException {
		
		addCust.clickOnAddNew();
		Thread.sleep(2000);
	}
	
	@Then("User can view Add new Customer Page")
	public void user_can_view_add_new_customer_page() {
		
	    Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
	}
	
	@When("User enter customer Info")
	public void user_enter_customer_info() throws InterruptedException {
		logger.info("***************** Adding customer data **********************");
		String email = randomString()+"@gmail.com";
		addCust.setEmail(email);
		addCust.setPassword("test123");
		addCust.setFirstName("Developer");	
		addCust.setLastName("Ravi");
		addCust.setGender("Male");
		addCust.setDateofBirth("3/1/1995");
		addCust.setCompay("NewLine pvt Ltd.");
		addCust.checkIsTaxExempt();
		addCust.selectNewsLetter("Test store 2");
		addCust.selectcustomerRole("Guests");
		addCust.selectVendor("Vendor 1");
		addCust.setAdminComment("This is created for Testing");
	    
	}
	
	@When("Click on Save button")
	public void click_on_save_button() throws InterruptedException {
		logger.info("***************** Saving Customer **********************");
		addCust.clickOnSave();
		Thread.sleep(3000);
	}
	
	@Then("User can view Confirmation message {string}")
	public void user_can_view_confirmation_message(String string) {
		
		
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText().
				contains("The new customer has been added successfully."));
		logger.info("***************** Customer Added Successfully **********************");
			   
	}
	
	// Steps to search the customer with Email/ First Name and Last Name
	
	@When("Enter Customer {string} as a {string}")
	public void enter_customer_as_a(String field, String value) {
		
		searchCust = new SearchCustomerPage(driver);
		searchCust.enterValuInInputBox(field, value);
	}
	
	@Then("User Should found {string} in the Search Table")
	public void user_should_found_in_the_search_table(String data) {
		
		searchCust.searchCustomer(data);
	}

	@When("Click on Search button")
	public void click_on_search_button() throws InterruptedException {
	   logger.info("***************** Clicked on Search Button **********************");
	   searchCust.clickSearch();
	   Thread.sleep(2000);
	}
	
	
	
	
}
