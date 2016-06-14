package skroll.testing;



import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class GoEuroSearch {
    static WebDriver driver;
    static Wait<WebDriver> wait;

    public static void main(String[] args) {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 30);
        driver.get("http://www.goeuro.com/");

        boolean result;
        try {
            result = firstPageContainsQAANet();
        } catch(Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            driver.close();
        }

        System.out.println("Test " + (result? "passed." : "failed."));
        if (!result) {
            System.exit(1);
        }
    }

    private static boolean firstPageContainsQAANet() {
        //type search query
    	driver.findElement(By.id("from_filter")).sendKeys("Berlin");
        try {
 			Thread.sleep(1000);
 		} catch (InterruptedException e) {
 			e.printStackTrace();
 		}
        driver.findElement(By.id("to_filter")).click();
        
        // click search
        driver.findElement(By.id("to_filter")).sendKeys("Prague");
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        driver.findElement(By.id("from_filter")).click();
        driver.findElement(By.id("search-form__submit-btn")).submit();
        
        // Wait for search to complete
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#results-train div.price-cell-content span.price-no span")));
        List<Float> floatList = new ArrayList<Float>();
        List<WebElement> result = driver.findElements(By.cssSelector("#results-train div.price-cell-content span.price-no span"));
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
}