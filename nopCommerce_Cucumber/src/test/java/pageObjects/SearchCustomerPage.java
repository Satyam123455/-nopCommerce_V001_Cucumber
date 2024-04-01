package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class SearchCustomerPage {
	
	public WebDriver ldriver;
	public WaitHelper waithelper;

	public SearchCustomerPage(WebDriver rdriver)
	{
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
		 waithelper = new WaitHelper(ldriver);
	}
	
	@FindBy(id = "SearchEmail")
	@CacheLookup
	WebElement searchEmail;
	
	@FindBy(id = "search-customers")
	@CacheLookup
	WebElement btnSearch;
	
	@FindBy(xpath = "//div[@class='dataTables_scrollBody']//table//tbody//tr//td[2]")
	@CacheLookup
	WebElement verifyEmail;
	
	@FindBy(id = "SearchFirstName")
	@CacheLookup
	WebElement searchFirstName;
	
	@FindBy(id = "SearchLastName")
	@CacheLookup
	WebElement searchLastName;
	
	@FindBy(id = "SearchMonthOfBirth")
	@CacheLookup
	WebElement dropdbMonth;
	
	@FindBy(id = "SearchDayOfBirth")
	@CacheLookup
	WebElement dropdbDay;
	
	@FindBy(id = "SearchCompany")
	@CacheLookup
	WebElement searchCompany;
	
	@FindBy(id = "SearchIpAddress")
	@CacheLookup
	WebElement searchIpAddress;
	
	@FindBy(xpath = "//div[@class='k-multiselect-wrap k-floatwrap']")
	@CacheLookup
	WebElement customerRoles;
	
	@FindBy(xpath="//label[contains(text(), 'Customer roles')]//following::span[@aria-label='delete']")
	@CacheLookup
	WebElement deleteRole;
	
	@FindBy(xpath="//table[@id='customers-grid']")
	@CacheLookup
	WebElement table;
	
	@FindBy(xpath="//table[@id='customers-grid']//tbody/tr")
	@CacheLookup
	List<WebElement> tableRows;
	
	@FindBy(xpath="//table[@id='customers-grid']//tbody/tr/td")
	@CacheLookup
	List<WebElement> tableColumns;
	
	String listBox = "//div[@class='k-list-scroller']//ul//li[contains(text(), '%s')]";
	
	String datebox = "//label[contains(text(), '%s')]//following::span//input[@class = 'k-input']";
	
	String inputBox = "(//label[contains(text(), '%s')]//following::input[@class='form-control text-box single-line'])[1]";
	
		
	public void enterValuInInputBox(String fieldName, String Value)
	{
		waithelper.WaitForElement(searchFirstName, 20);
		ldriver.findElement(By.xpath(String.format(inputBox, fieldName))).clear();
		ldriver.findElement(By.xpath(String.format(inputBox, fieldName))).sendKeys(Value);
	}
	
	
	public void enterDates(String dateField, String date)
	{
		ldriver.findElement(By.xpath(String.format(datebox, dateField))).sendKeys(date);
	}
	
	
	public void selectcustomerRole(String cRole) throws InterruptedException
	{
		deleteRole.click();
		customerRoles.click();
		Thread.sleep(2000);
		ldriver.findElement(By.xpath(String.format(listBox, cRole))).click();
	}
	
	public void clickSearch()
	{
		btnSearch.click();
		waithelper.WaitForElement(btnSearch, 20);
	}
	
	public int getNoOfRows()
	{
		return(tableRows.size());
	}
	
	public int getNoOfColumns()
	{
		return(tableColumns.size());
	}
	
	public boolean searchCustomer(String value)
	{
		boolean flag = false;
		
		for(int i=1;i<=getNoOfRows();i++)
		{
			String value1 = table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td")).getText();
			System.out.println(value1);
			
			if(value1.equals(value))
			{
				flag = true;
			}
		}
		
		return flag;
	}

}
