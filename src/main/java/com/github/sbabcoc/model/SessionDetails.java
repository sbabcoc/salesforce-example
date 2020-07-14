package com.github.sbabcoc.model;

import java.util.Date;
import java.util.Map;

import org.openqa.selenium.By;
import com.nordstrom.automation.selenium.model.ComponentContainer;
import com.nordstrom.automation.selenium.model.ShadowRoot;

/**
 * This class models the session details shadow DOM component.
 */
public class SessionDetails extends ShadowRoot {

    public SessionDetails(By locator, ComponentContainer parent) {
        super(locator, parent);
    }

    protected enum Using implements ByEnum {
        CLASS_TITLE(By.cssSelector("h2")),
        CLASS_TIME(By.cssSelector("p.time")),
        CLASS_ROOM(By.cssSelector("p.room")),
        ABSTRACT(By.cssSelector("div.abstract")),
        SPEAKER(By.cssSelector("div.speaker-list my-speaker-card"));
        
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
     * Get the title for this session.
     * 
     * @return session title
     */
    public String getTitle() {
        return findElement(Using.CLASS_TITLE).getText();
    }
    
    /**
     * Get the time for this session.
     * 
     * @return session time
     */
    public Date getTime() {
        return ClassTimeFormat.parse(findElement(Using.CLASS_TIME).getText());
    }
    
    /**
     * Get the room for this session.
     * 
     * @return session room
     */
    public String getRoom() {
        return findElement(Using.CLASS_ROOM).getText();
    }
    
    /**
     * Get the mapped collection of speakers for this session.
     * 
     * @return speaker map
     */
    public Map<Object, SpeakerCard> getSpeakerMap() {
        return newComponentMap(SpeakerCard.class, Using.SPEAKER.locator);
    }
    
}
