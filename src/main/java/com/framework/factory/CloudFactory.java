package com.framework.factory;
import com.microsoft.playwright.*;
public class CloudFactory implements BrowserFactory {
    private final String ws;
    public CloudFactory(String ws) { this.ws = ws; }
    public Page createPage(boolean headless) {
        Playwright pw = Playwright.create();
        Browser b = pw.chromium().connect(ws);
        return b.newContext().newPage();
    }
}
