package com.framework.factory;

import com.microsoft.playwright.*;

public class ChromiumStrategy implements BrowserStrategy {
    @Override
    public Page createDriver(boolean headless) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(headless)
        );
        BrowserContext context = browser.newContext();
        return context.newPage();
    }

}
