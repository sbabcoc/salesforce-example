package com.github.sbabcoc.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.nordstrom.automation.selenium.annotations.PageUrl;
import com.nordstrom.automation.selenium.interfaces.DetectsLoadCompletion;
import com.nordstrom.automation.selenium.support.Coordinator;
import com.nordstrom.automation.selenium.support.Coordinators;

/**
 * This class models the session details page of the <b>Lightning Conference</b> example site.
 */
@PageUrl("/")
public class SessionDetailsPage extends AppPage implements DetectsLoadCompletion {

    private SessionDetails sessionDetails;
    private final Coordinator<WebElement> detailsVisible;

    public SessionDetailsPage(WebDriver driver) {
        super(driver);
        detailsVisible = Coordinators.visibilityOfElementLocated(Using.SESSION_DETAILS.locator);
    }

    protected enum Using implements ByEnum {
        SESSION_DETAILS(By.cssSelector("my-session-details"));
        
        private By locator;
        
        Using(By locator) {
            this.locator = locator;
        }

        @Override
        public By locator() {
            return locator;
        }
    }
    
    /**
     * Get session details page component.
     * 
     * @return session details component
     */
    public SessionDetails getSessionDetails() {
        if (sessionDetails == null) {
            sessionDetails = new SessionDetails(Using.SESSION_DETAILS.locator, getAppRoot());
        }
        return sessionDetails;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <b>NOTE</b>: This page has finished loading when the session details component is visible.
     */
    @Override
    public boolean isLoadComplete() {
        return null != detailsVisible.apply(getAppRoot());
    }

}
