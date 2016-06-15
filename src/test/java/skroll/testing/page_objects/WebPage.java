package skroll.testing.page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public WebPage(WebDriver driver){
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 30);
	}
	
	public WebPage andReturn(Class<?> pageObject, WebDriver driver) {
		return (WebPage) PageFactory.initElements(driver, pageObject);
	}

	protected void shortWait() {
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
