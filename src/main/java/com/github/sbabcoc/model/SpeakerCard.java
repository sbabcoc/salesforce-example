package com.github.sbabcoc.model;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import com.nordstrom.automation.selenium.core.ByType;
import com.nordstrom.automation.selenium.core.JsUtility;
import com.nordstrom.automation.selenium.model.ComponentContainer;
import com.nordstrom.automation.selenium.model.RobustWebElement;
import com.nordstrom.automation.selenium.model.ShadowRoot;

/**
 * This class models the speaker card shadow DOM component.
 */
public class SpeakerCard extends ShadowRoot {
    
    private static final String EXTRACT_BLURB = 
            "return arguments[0].querySelector(arguments[1]).lastChild.textContent;";

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
    
    /**
     * Get the title of this speaker card (the speaker's name).
     * 
     * @return speaker card title
     */
    public String getTitle() {
        return (String) getKey(this);
    }
    
    /**
     * Get the e-mail address of this speaker card.
     * 
     * @return speaker card e-mail
     */
    public String getEmail() {
        return findElement(Using.EMAIL).getText();
    }
    
    /**
     * Get the "blurb" of this speaker card.
     * <p>
     * <b>NOTE</b>: The "blurb" is a short, pithy statement from/about the speaker.
     * 
     * @return speaker card "blurb"
     */
    public String getBlurb() {
        return JsUtility
                .runAndReturn(getDriver(), EXTRACT_BLURB, getWrappedContext(), ByType.cssLocatorFor(Using.CARD));
    }
    
    /**
     * Get the unique key for the speaker card with the specified context.
     * <p>
     * <b>NOTE</b>: The speaker card title serves as the unique key.
     * 
     * @param context speaker card component context
     * @return speaker card key
     */
    public static Object getKey(SearchContext context) {
        return getShadowRoot(context).findElement(Using.TITLE.locator).getText();
    }

}
