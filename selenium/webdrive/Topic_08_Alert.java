package webdrive;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Alert {
	WebDriver driver;
	
	// Dung thu vien Alert của Alert Selenium Thu vien Open Selenium
	Alert alert;
	WebDriverWait explicitWait;
	By resultValue = By.xpath("//p[@id='result']");
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver,10);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// Bat Alert
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		
		// Cach 2: Vua wait vua swich vao
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		// Get text de verify
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		// Accept Alert
		alert.accept();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(resultValue).getText(), "You clicked an alert successfully");
	}

	@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// Bat Alert
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		
		// Cach 2: Vua wait vua swich vao
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		// Get text de verify
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		// Accept Alert
		alert.dismiss();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(resultValue).getText(), "You clicked: Cancel");
			
	}

	@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		String alertValue = "Selenium WebDriver";
		// Bat Alert
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		
		// Cach 2: Vua wait vua swich vao
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		// Get text de verify
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		// Accept Alert
		alert.sendKeys(alertValue);
		alert.accept();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(resultValue).getText(), "You entered: "+ alertValue);
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time + 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}