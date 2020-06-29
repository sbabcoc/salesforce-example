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
    
    public String getTitle() {
    	return (String) getKey(this);
    }
    
    public Date getTime() {
    	return ClassTimeFormat.parse(findElement(Using.CLASS_TIME).getText());
    }
    
    public String getRoom() {
    	return findElement(Using.CLASS_ROOM).getText();
    }
    
    public List<String> getSpeakers() {
    	List<String> speakerList = new ArrayList<>();
    	for (WebElement thisSpeaker : findElements(Using.SPEAKER)) {
    		speakerList.add(thisSpeaker.getAttribute("alt"));
    	}
    	return speakerList;
    }
    
	public static Object getKey(SearchContext context) {
		return context.findElement(Using.CLASS_TITLE.locator).getText();
	}

}
