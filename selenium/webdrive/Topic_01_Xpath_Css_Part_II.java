package webdrive;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_01_Xpath_Css_Part_II {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
	}

	@Test
	public void Login_01_Empty_Email_And_Password() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		//Empty email
		driver.findElement(By.id("email")).sendKeys("");
		
		// Empty PW
		driver.findElement(By.name("login[password]")).sendKeys("");
		
		// Click Login button
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		//Expected text tra ve = requirement
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field");	
	}
	

	@Test
	public void Login_02_Invalid_Email_Address() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("1233@1333.333");
		driver.findElement(By.name("login[password]")).sendKeys("7890099");
		
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void Login_03_Invalid_Password() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("dam@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("111");
		
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	@Test
	public void Login_04_Incorrect_Password() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("dam@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("111111");
	
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),"Invalid login or password");
	}
	
	@Test
	public void Login_05_Incorrect_Email() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("viruss@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("111111111");
		
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),"Invalid login or password");
	
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}