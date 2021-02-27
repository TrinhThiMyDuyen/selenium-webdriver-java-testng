package webdrive;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_01_Xpath_Css_Part_I_01 {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
	}

	@Test
	public void TC_01_ID() {
		// De thao tac voi 1 element
		driver.findElement(By.id("FirstName")).sendKeys("Automation Testing");
		driver.findElement(By.id("Email")).sendKeys("atutomationtest@gmail.com");
		
	}

	@Test
	public void TC_02_Classname() {
		driver.findElement(By.className("search-box-text")).sendKeys("Macbook");
	}

	@Test
	public void TC_03_Name() {
		driver.findElement(By.name("Company")).sendKeys("Selenium");
	}
	
	@Test
	public void TC_04_Tagname() {
		System.out.println(driver.findElements(By.tagName("select")).size());
	}
	
	@Test
	public void TC_05_Link_Text() {
		Assert.assertTrue(driver.findElement(By.linkText("Register")).isDisplayed());
	}
	
	@Test
	public void TC_06_Partial_Link_Text() {
		Assert.assertTrue(driver.findElement(By.partialLinkText("downloads")).isDisplayed());
	}
	
	@Test
	public void TC_07_Css() {
		driver.findElement(By.cssSelector("#Password")).sendKeys("P@ss111111");
		driver.findElement(By.cssSelector("input[name='ConfirmPassword']")).sendKeys("P@ss111111");
	}
	
	@Test
	public void TC_08_Xpath() {
		driver.findElement(By.xpath("//input[@name='ConfirmPassword']")).clear();
		driver.findElement(By.xpath("//input[@name='ConfirmPassword']")).sendKeys("P@ss111111");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}