package com.github.sbabcoc.model;

import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.nordstrom.automation.selenium.annotations.InitialPage;
import com.nordstrom.automation.selenium.support.TestNgBase;

@InitialPage(LightningConferenceRoot.class)
public class ModelTest extends TestNgBase {
    
    private static final String CLASS_NAME = "The Future of JavaScript";
    private static final String CLASS_ROOM = "Keynote room";
    private static final List<String> CLASS_SPEAKERS = Arrays.asList("John Doe", "Laetitia Arevik");
    
    @Test
    public void checkSessionCount() {
        LightningConferenceRoot page = getInitialPage();
        Map<Object, ClassSession> sessionMap = page.getSessionList().getSessionMap();
        assertEquals(sessionMap.size(), 4);
    }
    
    @Test
    public void checkSessionDetails() {
        LightningConferenceRoot page = getInitialPage();
        Map<Object, ClassSession> sessionMap = page.getSessionList().getSessionMap();
        ClassSession session = sessionMap.get(CLASS_NAME);
        assertEquals(session.getRoom(), CLASS_ROOM);
        assertEquals(session.getSpeakers(), CLASS_SPEAKERS);
    }
    
    @Test
    public void checkSessionRoot() {
        LightningConferenceRoot page = getInitialPage();
        SessionDetailsPage sessionPage = page.getSessionList().openSessionPage(CLASS_NAME);
        SessionDetails sessionDetails = sessionPage.getSessionDetails();
        assertEquals(sessionDetails.getRoom(), CLASS_ROOM);
    }
    
    @Test
    public void checkSpeakerMap() {
        LightningConferenceRoot page = getInitialPage();
        SessionDetailsPage sessionPage = page.getSessionList().openSessionPage(CLASS_NAME);
        SessionDetails sessionDetails = sessionPage.getSessionDetails();
        Map<Object, SpeakerCard> speakerMap = sessionDetails.getSpeakerMap();
        assertEquals(speakerMap.keySet(), CLASS_SPEAKERS);
    }
}
