package com.github.sbabcoc.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import com.nordstrom.automation.selenium.model.ComponentContainer;
import com.nordstrom.automation.selenium.model.PageComponent;
import com.nordstrom.automation.selenium.model.RobustWebElement;

/**
 * This class defines a page component that models a single class session in
 * the session list.
 */
public class ClassSession extends PageComponent {

    public ClassSession(By locator, ComponentContainer parent) {
        super(locator, parent);
    }
    
    public ClassSession(RobustWebElement element, ComponentContainer parent) {
        super(element, parent);
    }
    
    protected enum Using implements ByEnum {
        CLASS_TITLE(By.cssSelector("p.title")),
        CLASS_TIME(By.cssSelector("p.time")),
        CLASS_ROOM(By.cssSelector("p.room")),
        SPEAKER(By.cssSelector("div.speaker-pictures img"));
        
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
     * Get the title for this class session.
     * 
     * @return class session title
     */
    public String getTitle() {
        return (String) getKey(this);
    }
    
    /**
     * Get the time for this class session.
     * 
     * @return class session time
     */
    public Date getTime() {
        return ClassTimeFormat.parse(findElement(Using.CLASS_TIME).getText());
    }
    
    /**
     * Get the room for this class session.
     * 
     * @return class session room
     */
    public String getRoom() {
        return findElement(Using.CLASS_ROOM).getText();
    }
    
    /**
     * Get the list of speakers for this class session.
     * 
     * @return class session speakers list
     */
    public List<String> getSpeakers() {
        List<String> speakerList = new ArrayList<>();
        for (WebElement thisSpeaker : findElements(Using.SPEAKER)) {
            speakerList.add(thisSpeaker.getAttribute("alt"));
        }
        return speakerList;
    }
    
    /**
     * Get the unique key for the class session with the specified context.
     * <p>
     * <b>NOTE</b>: The class session title serves as the unique key.
     * 
     * @param context class session component context
     * @return class session key
     */
    public static Object getKey(SearchContext context) {
        return context.findElement(Using.CLASS_TITLE.locator).getText();
    }

}
