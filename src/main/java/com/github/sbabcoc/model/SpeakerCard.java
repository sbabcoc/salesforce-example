package com.github.sbabcoc.model;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import com.nordstrom.automation.selenium.core.WebDriverUtils;
import com.nordstrom.automation.selenium.model.ComponentContainer;
import com.nordstrom.automation.selenium.model.RobustWebElement;
import com.nordstrom.automation.selenium.model.ShadowRoot;

public class SpeakerCard extends ShadowRoot {
    
    private static final String EXTRACT_BLURB = "return arguments[0].lastChild.textContent;";

    public SpeakerCard(By locator, ComponentContainer parent) {
        super(locator, parent);
    }
    
    public SpeakerCard(RobustWebElement element, ComponentContainer parent) {
        super(element, parent);
    }

    protected enum Using implements ByEnum {
        TITLE(By.cssSelector("p.title")),
        EMAIL(By.cssSelector("p.email")),
        CARD(By.cssSelector("div.card"));
        
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
    
    public String getEmail() {
        return findElement(Using.EMAIL).getText();
    }
    
    public String getBlurb() {
        WebElement card = findElement(Using.CARD);
        return (String) WebDriverUtils.getExecutor(this).executeScript(EXTRACT_BLURB, card);
    }
    
    public static Object getKey(SearchContext context) {
        return context.findElement(Using.TITLE.locator).getText();
    }

}
