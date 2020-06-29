package com.github.sbabcoc.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.nordstrom.automation.selenium.model.ComponentContainer;
import com.nordstrom.automation.selenium.model.Page;
import com.nordstrom.automation.selenium.model.ShadowRoot;

public abstract class AppPage extends Page {
	
	private AppRoot appRoot;

	public AppPage(WebDriver driver) {
		super(driver);
	}
	
	public AppRoot getAppRoot() {
		if (appRoot == null) {
			appRoot = new AppRoot(By.cssSelector("my-app"), this);
		}
		return appRoot;
	}
	
	public static class AppRoot extends ShadowRoot {

		public AppRoot(By locator, ComponentContainer parent) {
			super(locator, parent);
		}
		
	}

}
