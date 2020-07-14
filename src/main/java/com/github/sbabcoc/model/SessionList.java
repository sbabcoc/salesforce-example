package com.github.sbabcoc.model;

import java.util.Map;

import org.openqa.selenium.By;

import com.nordstrom.automation.selenium.model.ComponentContainer;
import com.nordstrom.automation.selenium.model.ShadowRoot;

/**
 * This class models the session list shadow DOM component.
 */
public class SessionList extends ShadowRoot {

    public SessionList(By locator, ComponentContainer parent) {
        super(locator, parent);
    }

    protected enum Using implements ByEnum {
        SEARCH_FIELD(By.cssSelector("input[type=search]")),
        CLASS_SESSION(By.cssSelector("a.session"));
        
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
     * Apply the specified filter to the list of sessions.
     *  
     * @param filter session filter string
     */
    public void searchFor(String filter) {
        updateValue(findElement(Using.SEARCH_FIELD), filter);
    }
    
    /**
     * Get map of session keyed by class name.
     * <p>
     * <b>NOTE</b>: The returned map will only contain visible sessions, which
     * are affected by the currently applied filter.
     * 
     * @return session map
     * @see #searchFor(String)
     */
    public Map<Object, ClassSession> getSessionMap() {
        return newComponentMap(ClassSession.class, Using.CLASS_SESSION.locator);
    }
    
    /**
     * Determine if the specified session is available.
     * <p>
     * <b>NOTE</b>: The currently applied filter affects session availability.
     * 
     * @param className name of session in question
     * @return {@code true} if session is available; otherwise {@code false}
     * @see #searchFor(String)
     */
    public boolean hasSession(String className) {
        return getSessionMap().containsKey(className);
    }
    
    /**
     * Open the details page for the specified session.
     * 
     * @param className name of target session
     * @return session details page
     */
    public SessionDetailsPage openSessionPage(String className) {
        Map<Object, ClassSession> sessionMap = getSessionMap();
        ClassSession session = sessionMap.get(className);
        if (session != null) {
            session.getWrappedElement().click();
            return new SessionDetailsPage(driver);
        }
        throw new IllegalStateException("Class session not found: " + className);
    }
    
}
