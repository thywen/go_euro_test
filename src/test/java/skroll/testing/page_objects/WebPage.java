package skroll.testing.page_objects;

import org.openqa.selenium.WebDriver;

public class WebPage {
	WebDriver driver;
	
	public WebPage(WebDriver driver){
		this.driver = driver;
	}

	protected void shortWait() {
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
