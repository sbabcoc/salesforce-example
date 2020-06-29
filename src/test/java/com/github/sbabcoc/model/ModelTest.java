package com.github.sbabcoc.model;

import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.testng.annotations.Test;

import com.nordstrom.automation.selenium.annotations.InitialPage;
import com.nordstrom.automation.selenium.support.TestNgBase;

@InitialPage(LightningConferenceRoot.class)
public class ModelTest extends TestNgBase {
	
	@Test
	public void checkSessionCount() {
		LightningConferenceRoot page = (LightningConferenceRoot) getInitialPage();
		Map<Object, ClassSession> sessionMap = page.getSessionList().getSessionMap();
		assertEquals(sessionMap.size(), 4);
	}
	
	@Test
	public void check() {
		LightningConferenceRoot page = (LightningConferenceRoot) getInitialPage();
		SessionDetailsPage sessionPage = page.getSessionList().openSessionPage("The Future of JavaScript");
	}
}
