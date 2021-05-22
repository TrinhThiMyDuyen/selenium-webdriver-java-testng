package webdrive;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_UserInteractions_Part_I {
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	@BeforeClass
	public void beforeClass() {

		// Gan driver vao moi dung dc cho thu vien
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		action = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Hover_1() {
		driver.get("https://jqueryui.com/resources/demos/tooltip/default.html");
		
		action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
		sleepInSecond(3);
		
		// We ask for your age only for statistical purposes.
		Assert.assertEquals(driver.findElement(By.cssSelector(".ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
	}
	
	@Test
	public void TC_01_Hover_2() {
		driver.get("https://www.myntra.com/");
		
		action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Kids']"))).perform();
		sleepInSecond(3);
		
		// Action click
		action.click(driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Home & Bath']"))).perform();
	
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text()='Kids Home Bath']")).isDisplayed());	
	}
	
	@Test
	public void TC_02_Click_And_Hold_1() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List<WebElement> rectangles = driver.findElements(By.cssSelector(".ui-selectee"));
		
		// Ham tim findelements List: Tim cho 12 thang 
		// Lay ra duoc 12 cai element
		/* index (0-11)
		 * element từ e1 ~ e5
		// Get click hay gi do de tro thanh element */
		// rectangles.get(2).click();
		
		action.clickAndHold(rectangles.get(0))  // click vao o so 1 chua nha chuot
		.moveToElement(rectangles.get(3))       // di chuot den o so 4 index = 3
		.release()   // Ham nha chuot trai
		.perform();                             // Thuc thi cac action không perform thi ko chay
		
		sleepInSecond(5);
		
		Assert.assertEquals(driver.findElements(By.cssSelector(".ui-selectee.ui-selected")).size(),4);
	}

	@Test
	public void TC_02_Click_And_Hold_2() {
		
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List<WebElement> rectangles = driver.findElements(By.cssSelector(".ui-selectee"));
		
		// Ham tim findelements List: Tim cho 12 thang 
		// Lay ra duoc 12 cai element
		/* index (0-11)
		 * element từ e1 ~ e5
		// Get click hay gi do de tro thanh element */
		// rectangles.get(2).click();
		//action.keyDown(rectangles.get(0), Keys.CONTROL).perform();
		
        action.keyDown(Keys.CONTROL).perform(); // Nhan phim ctrl xuong chua nha ra
        action.click(rectangles.get(0))
        .click(rectangles.get(2))
        .click(rectangles.get(5))
        .click(rectangles.get(10)).perform(); // Thuc thi
        //action.keyDown(Keys.CONTROL).perform(); // Nha phim ctrl ra
        action.keyUp(Keys.CONTROL).perform();
        
		sleepInSecond(5);
		
		Assert.assertEquals(driver.findElements(By.cssSelector(".ui-selectee.ui-selected")).size(),4);		
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