package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {
	
	public WebDriver ldriver;

	public AddCustomerPage(WebDriver rdriver)
	{
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	// WebElements
	
	@FindBy(xpath = "//a[@href='#']//p[contains(text(), 'Customers')]")
	@CacheLookup
	WebElement customers;
	
	@FindBy(xpath = "(//ul//li[@class='nav-item']//a//p[contains(text(), 'Customers')])[1]")
	@CacheLookup
	WebElement menuCustomers;
	
	@FindBy(xpath = "(//div[@class='k-multiselect-wrap k-floatwrap'])[1]")
	@CacheLookup
	WebElement newsLetter;
	
	@FindBy(xpath = "(//div[@class='k-multiselect-wrap k-floatwrap'])[2]")
	@CacheLookup
	WebElement customerRole;
	
	@FindBy(xpath = "//select[@id='VendorId']")
	@CacheLookup
	WebElement vendor;
	
	@FindBy(xpath = "//a[@class='btn btn-primary']")
	@CacheLookup
	WebElement addNew;
	
	@FindBy(xpath="//label[contains(text(), 'Customer roles')]//following::span[@aria-label='delete']")
	@CacheLookup
	WebElement deleteRole;
	
	String fieldName;
	
	String inputBox = "//div[@class='label-wrapper']//label//following::input[@id='%s']";
	
	String listBox = "//div[@class='k-list-scroller']//ul//li[contains(text(), '%s')]";
		
	String adminComment = "//div[@class='col-md-9']//textarea[@id='%s']";
	
	@FindBy(xpath = "//button[@name='save']")
	@CacheLookup
	WebElement btnSave;
	
	
	// Action Methods
	
	public String getPageTitle()
	{
		return ldriver.getTitle();
	}
	
	public void clickOnCustomersMenu()
	{
		customers.click();
	}
	
	public void clickOnCustomersMenuItem()
	{
		menuCustomers.click();
	}
	
	public void clickOnAddNew()
	{
		addNew.click();
	}
	
	public void setEmail(String email) throws InterruptedException
	{
		fieldName = "Email";
		Thread.sleep(1000);
		ldriver.findElement(By.xpath(String.format(inputBox, fieldName))).sendKeys(email);
	}
	
	public void setPassword(String password) throws InterruptedException
	{
		fieldName = "Password";
		Thread.sleep(1000);
		ldriver.findElement(By.xpath(String.format(inputBox, fieldName))).sendKeys(password);
	}
	
	public void setFirstName(String fname) throws InterruptedException
	{
		fieldName = "FirstName";
		Thread.sleep(1000);
		ldriver.findElement(By.xpath(String.format(inputBox, fieldName))).sendKeys(fname);
	}
	
	public void setLastName(String lname) throws InterruptedException
	{
		fieldName = "LastName";
		Thread.sleep(1000);
		ldriver.findElement(By.xpath(String.format(inputBox, fieldName))).sendKeys(lname);
	}
	
	public void setGender(String gender) throws InterruptedException
	{
		fieldName = "Gender_" + gender;
		Thread.sleep(1000);
		ldriver.findElement(By.xpath(String.format(inputBox, fieldName))).click();
	}
	
	public void setDateofBirth(String dob) throws InterruptedException
	{
		fieldName = "DateOfBirth";
		Thread.sleep(1000);
		ldriver.findElement(By.xpath(String.format(inputBox, fieldName))).sendKeys(dob);
	}
		
	public void setCompay(String companyName) throws InterruptedException
	{
		fieldName = "Company";
		Thread.sleep(1000);
		ldriver.findElement(By.xpath(String.format(inputBox, fieldName))).sendKeys(companyName);
	}
	
	public void checkIsTaxExempt()
	{
		fieldName = "IsTaxExempt";
		ldriver.findElement(By.xpath(String.format(inputBox, fieldName))).click();
	}
	
	public void selectNewsLetter(String listValue) throws InterruptedException
	{
		newsLetter.click();
		newsLetter.click();
		Thread.sleep(2000);
		ldriver.findElement(By.xpath(String.format(listBox, listValue))).click();
	}
	
	public void selectcustomerRole(String cRole) throws InterruptedException
	{
		deleteRole.click();
		customerRole.click();
		Thread.sleep(2000);
		ldriver.findElement(By.xpath(String.format(listBox, cRole))).click();
	}
	
	public void selectVendor(String value) throws InterruptedException
	{	
		Thread.sleep(2000);
		Select s = new Select(vendor);
		s.selectByVisibleText(value);
	}
		
	public void setAdminComment(String admincomment)
	{
		fieldName = "AdminComment";
		ldriver.findElement(By.xpath(String.format(adminComment, fieldName))).sendKeys(admincomment);
	}

	public void clickOnSave()
	{
		btnSave.click();
	}
}
