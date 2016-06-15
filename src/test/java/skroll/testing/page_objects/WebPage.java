package skroll.testing.page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class WebPage {
	WebDriver driver;
	
	public WebPage(WebDriver driver){
		this.driver = driver;
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
