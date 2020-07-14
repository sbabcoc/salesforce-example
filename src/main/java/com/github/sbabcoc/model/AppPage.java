package com.github.sbabcoc.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.nordstrom.automation.selenium.model.ComponentContainer;
import com.nordstrom.automation.selenium.model.Page;
import com.nordstrom.automation.selenium.model.ShadowRoot;

/**
 * This abstract base class provides common structure and functionality used by
 * the concrete page classes in this model.
 */
public abstract class AppPage extends Page {
    
    private AppRoot appRoot;

    public AppPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Get the root shadow DOM container for this page.
     * 
     * @return shadow DOM root context
     */
    public AppRoot getAppRoot() {
        if (appRoot == null) {
            appRoot = new AppRoot(By.cssSelector("my-app"), this);
        }
        return appRoot;
    }
    
    /**
     * This class defines the shadow DOM root context for the page.
     */
    public static class AppRoot extends ShadowRoot {

        public AppRoot(By locator, ComponentContainer parent) {
            super(locator, parent);
        }
        
    }

}
