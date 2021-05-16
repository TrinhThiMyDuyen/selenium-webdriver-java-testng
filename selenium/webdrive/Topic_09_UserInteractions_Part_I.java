package webdrive;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_UserInteractions_Part_I {
	WebDriver driver;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		System.set
		driver = new FirefoxDriver();
		// Gan driver vao moi dung dc cho thu vien
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Hover_1() {
		driver.get("https://jqueryui.com/resources/demos/tooltip/default.html");
		
		action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
		sleepInSecond(3);
		
		// We ask for your age only for statistical purposes.
		Assert.assertEquals(driver.findElement(By.cssSelector(".ui")), "We ask for your age only for statistical purposes.");
	}
	
	@Test
	public void TC_01_Hover_2() {
		driver.get("https://www.myntra.com/");
		
		action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Kids']"))).perform();
		sleepInSecond(3);	
	}
	

	@Test
	public void TC_02_Click_And_Hold_1() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List<WebElement> rectangles = driver.findElement(By.cssSelector(".ui-selectee"));
		
		// Lay ra duoc 12 cai element
		// Get click hay gi do de tro thanh element
		rectangles.get(2).click();
		
		action.clickAndHold(rectangles.get(0))  // click vao o so 1 chua nha chuot
		.moveToElement(rectangles.get(3))       // di chuot den o so 4
		.release()
		.perform();                             // Thuc thi cac action
		
		sleepInSecond(5);
		
		Assert.Assert			
	}

	@Test
	public void TC_02_Click_And_Hold_2() {
		
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List<WebElement> rectangles = driver.findElement(By.cssSelector(".ui-selectee"));
		
		// Lay ra duoc 12 cai element
		// Get click hay gi do de tro thanh element
		rectangles.get(2).click();
		
		action.keyUp(Keys.CONTROL).perform(); // Nhan phim Ctrl xuong (chua nha ra)
		action.click
		
		sleepInSecond(5);
		
		Assert.Assert			
	}

	
	@Test
	public void TC_03_Double_Click() {
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