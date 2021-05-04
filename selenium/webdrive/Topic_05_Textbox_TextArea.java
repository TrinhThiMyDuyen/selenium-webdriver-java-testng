package webdrive;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Textbox_TextArea {
	WebDriver driver;
	String emailAddress, loginPageUrl, userID, password;
	String name, dateOfBirth, address, city,state, pin, phone, gender, customerID;
	String editaddress, editcity,editstate, editpin, editphone,editemailAddress;

	By nameTextbox = By.name("name");
	By genderRadio = By.xpath("//input[@value='m']");
	By genderTextbox = By.name("gender");
	By dobTextbox = By.name("dob");
	By addressTextArea = By.name("addr");
	By cityTextbox = By.name("city");
	By stateTextbox = By.name("state");
	By pinTextbox = By.name("pinno");
	By phoneTextbox = By.name("telephoneno");
	By emailTextbox = By.name("emailid");
	By passwordTextbox = By.name("password");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://demo.guru99.com/v4/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		emailAddress = getRandomEmail();
		name = "My Duyen";
		dateOfBirth = "1956-01-01";
		address = "Thanh Xuan Trung";
		city = "Ha Noi";
		state = "Hawai";
		pin = "123456";
		phone = "0987899999";
		
		editaddress = "Thanh Xuan Trung edit";
		editcity = "Ha Noi edit";
		editstate = "Hawai edit";
		editpin = "12345690";
		editphone = "098780000";
		editemailAddress = getRandomEmail();
	}

	@Test
	public void TC_01_Register() {
		loginPageUrl = driver.getCurrentUrl();

		driver.findElement(By.xpath("//a[text()='here']")).click();

		driver.findElement(By.name("emailid")).sendKeys(emailAddress);

		driver.findElement(By.name("btnLogin")).click();

		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

	}

	@Test
	public void TC_02_Login() {
		driver.get(loginPageUrl);

		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(),
				"Welcome To Manager's Page of Guru99 Bank");

	}

	@Test
	public void TC_03_New_Customer() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		driver.findElement(nameTextbox).sendKeys(name);
		driver.findElement(genderRadio).click();
		driver.findElement(dobTextbox).sendKeys(dateOfBirth);
		driver.findElement(addressTextArea).sendKeys(address);
		driver.findElement(cityTextbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(phoneTextbox).sendKeys(phone);
		driver.findElement(emailTextbox).sendKeys(emailAddress);
		driver.findElement(passwordTextbox).sendKeys(password);
		
		driver.findElement(By.name("sub")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(),
				"Customer Registered Successfully!!!");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),
				name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),
				gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Date of Birth']/following-sibling::td")).getText(),
				dateOfBirth);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),
				city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
				state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='PIN']/following-sibling::td")).getText(),
				pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile Number']/following-sibling::td")).getText(),
				phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='E-mail']/following-sibling::td")).getText(),
				emailAddress);
		
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();

	}

	@Test
	public void TC_04_Edit_Customer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		
		Assert.assertEquals(driver.findElement(nameTextbox).getAttribute("value"),
				name);
		Assert.assertEquals(driver.findElement(genderTextbox).getAttribute("value"),
				gender);
		Assert.assertEquals(driver.findElement(dobTextbox).getAttribute("value"),
				dateOfBirth);
		Assert.assertEquals(driver.findElement(addressTextArea).getText(),
				address);
		Assert.assertEquals(driver.findElement(cityTextbox).getAttribute("value"),
				city);
		Assert.assertEquals(driver.findElement(stateTextbox).getAttribute("value"),
				state);
		Assert.assertEquals(driver.findElement(pinTextbox).getAttribute("value"),
				pin);
		Assert.assertEquals(driver.findElement(phoneTextbox).getAttribute("value"),
				phone);
		Assert.assertEquals(driver.findElement(emailTextbox).getAttribute("value"),
				emailAddress);
		
		driver.findElement(addressTextArea).clear();
		driver.findElement(addressTextArea).sendKeys(editaddress);
		driver.findElement(addressTextArea).clear();
		driver.findElement(cityTextbox).sendKeys(editcity);
		driver.findElement(cityTextbox).clear();
		driver.findElement(stateTextbox).sendKeys(editstate);
		driver.findElement(stateTextbox).clear();
		driver.findElement(pinTextbox).sendKeys(editpin);
		driver.findElement(pinTextbox).clear();
		driver.findElement(phoneTextbox).sendKeys(editphone);
		driver.findElement(phoneTextbox).clear();
		driver.findElement(emailTextbox).sendKeys(editemailAddress);
		driver.findElement(emailTextbox).clear();
		
		driver.findElement(By.name("sub")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(),
				"Customer details updated Successfully!!!");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText(),
				customerID);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),
				name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),
				gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Date of Birth']/following-sibling::td")).getText(),
				dateOfBirth);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				editaddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),
				editcity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
				editstate);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='PIN']/following-sibling::td")).getText(),
				editpin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile Number']/following-sibling::td")).getText(),
				editphone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='E-mail']/following-sibling::td")).getText(),
				editemailAddress);
		

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

	public String getRandomEmail() {
		Random rand = new Random();
		return "johndeep" + rand.nextInt(9999) + "@hotmail.com";
	}

}