package skroll.testing.page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoEuroHomePage extends WebPage{

	
	@FindBy(id = "from_filter")
    WebElement fromField;
	@FindBy(id = "to_filter")
    WebElement toField;
	@FindBy(id = "search-form__submit-btn")
    WebElement submitButton;
	
	
	public GoEuroHomePage(WebDriver driver) {
		super(driver);
	}
	
	public void addFromCity(String city){
		fromField.sendKeys(city);
	}
	
	public void addToCity(String city) {
		toField.sendKeys(city);
	}
	
	public void submitSearch() {
		submitButton.submit();
	}
	
	public GoEuroSearchResultPage searchForConnections(String fromCity, String toCity) {
		addFromCity(fromCity);
		shortWait();
		toField.click();
		addToCity(toCity);
		shortWait();
		fromField.click();
		submitSearch();
		return (GoEuroSearchResultPage) andReturn(GoEuroSearchResultPage.class, driver);
	}
}
