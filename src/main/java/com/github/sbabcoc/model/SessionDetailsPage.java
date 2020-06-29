package com.github.sbabcoc.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.nordstrom.automation.selenium.annotations.PageUrl;

@PageUrl("/")
public class SessionDetailsPage extends AppPage {

	private SessionDetails sessionDetails;

	public SessionDetailsPage(WebDriver driver) {
		super(driver);
	}

    protected enum Using implements ByEnum {
		SESSION_DETAILS(By.tagName("my-session-details"));
    	
    	private By locator;
    	
    	Using(By locator) {
    		this.locator = locator;
    	}

		@Override
    	public By locator() {
			return locator;
		}
    }
    
    public SessionDetails getSessionDetails() {
    	if (sessionDetails == null) {
    		sessionDetails = new SessionDetails(Using.SESSION_DETAILS.locator, getAppRoot());
    	}
    	return sessionDetails;
    }

}
