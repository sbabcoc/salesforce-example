package com.github.sbabcoc.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.nordstrom.automation.selenium.annotations.PageUrl;

/**
 * This class models the root page of the <b>Lightning Conference</b> example site.
 */
@PageUrl("/")
public class LightningConferenceRoot extends AppPage {
    
    private SessionList sessionList;

    public LightningConferenceRoot(WebDriver driver) {
        super(driver);
    }
    
    protected enum Using implements ByEnum {
        SESSION_LIST(By.tagName("my-session-list"));
        
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
     * Get the session list page component.
     * 
     * @return session list component
     */
    public SessionList getSessionList() {
        if (sessionList == null) {
            sessionList = new SessionList(Using.SESSION_LIST.locator, getAppRoot());
        }
        return sessionList;
    }

}
