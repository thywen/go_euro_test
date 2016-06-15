package skroll.testing.page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoEuroHomePage {
	WebDriver driver;
	
	@FindBy(id = "from_filter")
    WebElement fromField;
	@FindBy(id = "to_filter")
    WebElement toField;
	@FindBy(id = "search-form__submit-btn")
    WebElement submitButton;
	
	
	public GoEuroHomePage(WebDriver driver) {
		this.driver = driver;
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
	
	public void searchForConnections(String fromCity, String toCity) {
		addFromCity(fromCity);
		shortWait();
		toField.click();
		addToCity(toCity);
		shortWait();
		fromField.click();
		submitSearch();
	}
	
	private void shortWait() {
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

}
