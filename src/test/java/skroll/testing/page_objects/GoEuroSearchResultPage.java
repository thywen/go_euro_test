package skroll.testing.page_objects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GoEuroSearchResultPage extends WebPage{
	WebDriver driver;

	private final String trainResultSelectorString = "#results-train span.price-no span";
	By trainResults = By.cssSelector(trainResultSelectorString);
	
	@FindBy(css = trainResultSelectorString)
	List<WebElement> trainResultCashValues;
	
	@FindBy(id = "tab_train")
	WebElement trainTab;
	
	private final String flightResultSelectorString = "#results-flight span.price-no span";
	By flightResults = By.cssSelector(flightResultSelectorString);
	
	@FindBy(css = flightResultSelectorString)
	List<WebElement> flightResultCashValues;
	
	@FindBy(id = "tab_flight")
	WebElement flightTab;
	
	private final String busResultSelectorString = "#results-bus span.price-no span";
	By busResults = By.cssSelector(busResultSelectorString);
	
	@FindBy(css = busResultSelectorString)
	List<WebElement> busResultCashValues;
	
	@FindBy(id = "tab_bus")
	WebElement busTab;
	
	
	public GoEuroSearchResultPage(WebDriver driver) {
		super(driver);
	}
	
	public boolean is_train_prizes_sorted() {
		if (!isTabActive(trainTab)){
			trainTab.click();
		}
		waitUntilVisibility(trainResults);
        return are_prizes_sorted(trainResultCashValues);
	}
	
	public boolean is_flight_prizes_sorted() {
		if (!isTabActive(flightTab)){
			busTab.click();
		}
		waitUntilVisibility(flightResults);
		return are_prizes_sorted(flightResultCashValues);
	}
	
	public boolean is_bus_prizes_sorted() {
		if (!isTabActive(busTab)){
			busTab.click();
		}
		waitUntilVisibility(busResults);
		return are_prizes_sorted(busResultCashValues);
	}
	
	private void waitUntilVisibility(By selector) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
	}
	
	private boolean isTabActive(WebElement tab){
		return tab.getAttribute("class").contains("active");
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
