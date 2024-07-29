package com.saucedemo.Browser;

public enum Browsers {
	CHROME("Google Chrome"),
	FIREFOX("Mozilla Firefox"), 
	EDGE("MicrosoftEdge"), 
	SAFARI("Mac Safari"), ;

	private String browserNameWithCompanies;

	Browsers(String browserName) {
		this.browserNameWithCompanies = browserName;
	}

	public String getBrowserNameWithCompanies() {
		return browserNameWithCompanies;
	}
}
