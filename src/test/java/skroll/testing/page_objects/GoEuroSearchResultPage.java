package skroll.testing.page_objects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoEuroSearchResultPage extends WebPage{
	WebDriver driver;
	
	@FindBy(css = "#results-train div.price-cell-content span.price-no span")
	WebElement trainResultCashValues;
	
	
	public GoEuroSearchResultPage(WebDriver driver) {
		super(driver);
		WebDriverWait wait = new WebDriverWait(driver, 30);
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
}
