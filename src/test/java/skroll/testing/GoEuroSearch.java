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

		 	homePage.searchForConnections(fromCity, toCity);
	        // Wait for search to complete
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#results-train div.price-cell-content span.price-no span")));
	        
	        List<WebElement> result = driver.findElements(By.cssSelector("#results-train div.price-cell-content span.price-no span"));
	        Assert.assertTrue(are_prizes_sorted(result));
	 }
	 
	 private boolean are_prizes_sorted(List<WebElement> result){
		 List<Float> floatList = new ArrayList<Float>();
	        for (int i = 0; i < result.size(); i++) {
	            WebElement element = result.get(i);
	            if (i%4 == 1) {
	            	float a = Float.parseFloat(element.getText() + '.' + result.get(i+2).getText());
	            	floatList.add(a);
	            }
	        }
	        for (int i = 0; i < floatList.size()-1; i++) {
	            Float floatAtIndex = floatList.get(i);
	            Float floatAtIndexPlusOne = floatList.get(i+1);
	            if (floatAtIndex > floatAtIndexPlusOne) {
	            	return false;
	            }
	        }
	        return true;
	 }
	 
	 @AfterClass
	 public static void closeBrowser(){
		 driver.quit();
	 }
}