package skroll.testing;



import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import skroll.testing.page_objects.GoEuroHomePage;
import skroll.testing.page_objects.GoEuroSearchResultPage;


public class GoEuroSearch {
    static WebDriver driver;
    static Wait<WebDriver> wait;
    
    String fromCity = "Berlin";
    String toCity = "Prague";
    
    static GoEuroHomePage homePage;
    
	 @Before
     public void openBrowser(){
         driver = new FirefoxDriver();
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 wait = new WebDriverWait(driver, 30);
		 driver.get("http://www.goeuro.com/");
		 homePage = PageFactory.initElements(driver, GoEuroHomePage.class);
		} 
	 

	 
	 @Test
	 public void sorting_of_train_prices(){
		 GoEuroSearchResultPage resultPage = homePage.searchForConnections(fromCity, toCity);
	     Assert.assertTrue(resultPage.is_train_prizes_sorted());
	 }
	 
	 @Test
	 public void sorting_of_flight_prices(){
		 GoEuroSearchResultPage resultPage = homePage.searchForConnections(fromCity, toCity);
	     Assert.assertTrue(resultPage.is_flight_prizes_sorted());
	 }
	 
	 @Test
	 public void sorting_of_bus_prices(){
		 GoEuroSearchResultPage resultPage = homePage.searchForConnections(fromCity, toCity);
	     Assert.assertTrue(resultPage.is_bus_prizes_sorted());
	 }
	 
	 @After
	 public void closeBrowser(){
		 driver.quit();
	 }
}