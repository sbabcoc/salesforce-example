package com.github.sbabcoc.model;

import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.testng.annotations.Test;

import com.nordstrom.automation.selenium.annotations.InitialPage;
import com.nordstrom.automation.selenium.support.TestNgBase;

@InitialPage(LightningConferenceRoot.class)
public class ModelTest extends TestNgBase {
    
    private static final String CLASS_NAME = "The Future of JavaScript";
    private static final String CLASS_ROOM = "Keynote room";
    
    @Test
    public void checkSessionCount() {
        LightningConferenceRoot page = (LightningConferenceRoot) getInitialPage();
        Map<Object, ClassSession> sessionMap = page.getSessionList().getSessionMap();
        assertEquals(sessionMap.size(), 4);
    }
    
    @Test
    public void checkSessionRoom() {
        LightningConferenceRoot page = (LightningConferenceRoot) getInitialPage();
        SessionDetailsPage sessionPage = page.getSessionList().openSessionPage(CLASS_NAME);
        SessionDetails sessionDetails = sessionPage.getSessionDetails();
        assertEquals(sessionDetails.getRoom(), CLASS_ROOM);
    }
}
