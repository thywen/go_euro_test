package skroll.testing;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import skroll.testing.page_objects.GoEuroHomePage;
import skroll.testing.page_objects.GoEuroSearchResultPage;


public class GoEuroSearch {
    static WebDriver driver;
    static Wait<WebDriver> wait;
    
	 @BeforeClass
     public static void openBrowser(){
         driver = new FirefoxDriver();
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} 
	 
	 @Test
	 public void sorting_of_prices(){
		 String fromCity = "Berlin";
		 String toCity = "Prague";
		 wait = new WebDriverWait(driver, 30);
		 driver.get("http://www.goeuro.com/");
		 
		 GoEuroHomePage homePage = PageFactory.initElements(driver, GoEuroHomePage.class);

		 GoEuroSearchResultPage resultPage = homePage.searchForConnections(fromCity, toCity);
	     Assert.assertTrue(resultPage.is_train_prizes_sorted());
	 }
	 
	 @AfterClass
	 public static void closeBrowser(){
		 driver.quit();
	 }
}