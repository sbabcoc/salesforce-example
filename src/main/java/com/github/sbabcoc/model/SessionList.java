package com.github.sbabcoc.model;

import java.util.Map;

import org.openqa.selenium.By;

import com.nordstrom.automation.selenium.model.ComponentContainer;
import com.nordstrom.automation.selenium.model.ShadowRoot;

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
    
    public void searchFor(String filter) {
        updateValue(findElement(Using.SEARCH_FIELD), filter);
    }
    
    public Map<Object, ClassSession> getSessionMap() {
        return newComponentMap(ClassSession.class, Using.CLASS_SESSION.locator);
    }
    
    public boolean hasSession(String className) {
        return getSessionMap().containsKey(className);
    }
    
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
