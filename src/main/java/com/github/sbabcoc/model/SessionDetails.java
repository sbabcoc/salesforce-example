package com.github.sbabcoc.model;

import java.util.Date;
import java.util.Map;

import org.openqa.selenium.By;
import com.nordstrom.automation.selenium.model.ComponentContainer;
import com.nordstrom.automation.selenium.model.ShadowRoot;

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
    
    public String getTitle() {
        return findElement(Using.CLASS_TITLE).getText();
    }
    
    public Date getTime() {
        return ClassTimeFormat.parse(findElement(Using.CLASS_TIME).getText());
    }
    
    public String getRoom() {
        return findElement(Using.CLASS_ROOM).getText();
    }
    
    public Map<Object, SpeakerCard> getSpeakerMap() {
        return newComponentMap(SpeakerCard.class, Using.SPEAKER.locator);
    }
    
}
