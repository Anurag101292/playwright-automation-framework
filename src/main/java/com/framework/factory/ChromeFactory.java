package com.framework.factory;
import com.microsoft.playwright.*;
public class ChromeFactory implements BrowserFactory {
    public Page createPage(boolean headless) {
        Playwright pw = Playwright.create();
        Browser b = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
        return b.newContext().newPage();
    }
}
