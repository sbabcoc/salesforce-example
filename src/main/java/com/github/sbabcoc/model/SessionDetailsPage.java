package com.github.sbabcoc.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.nordstrom.automation.selenium.annotations.PageUrl;
import com.nordstrom.automation.selenium.interfaces.DetectsLoadCompletion;
import com.nordstrom.automation.selenium.support.Coordinator;
import com.nordstrom.automation.selenium.support.Coordinators;

@PageUrl("/")
public class SessionDetailsPage extends AppPage implements DetectsLoadCompletion {

	private SessionDetails sessionDetails;
	private static final Coordinator<WebElement> detailsVisible;
	
	static {
		detailsVisible = Coordinators.visibilityOfElementLocated(By.tagName("my-session-details"));
	}

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

	@Override
	public boolean isLoadComplete() {
		return null != detailsVisible.apply(getAppRoot());
	}

}
