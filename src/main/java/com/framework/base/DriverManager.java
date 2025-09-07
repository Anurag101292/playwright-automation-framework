package com.framework.base;
import com.microsoft.playwright.*;
public class DriverManager {
    private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();
    private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> tlContext = new ThreadLocal<>();
    private static ThreadLocal<Page> tlPage = new ThreadLocal<>();
    public static void init(String browserName, boolean headless) {
        Playwright playwright = Playwright.create();
        tlPlaywright.set(playwright);
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
        tlBrowser.set(browser);
        BrowserContext ctx = browser.newContext();
        tlContext.set(ctx);
        tlPage.set(ctx.newPage());
        String base = ConfigManager.get("baseUrl"); if (base != null) tlPage.get().navigate(base);
    }
    public static Page getPage() { return tlPage.get(); }
    public static void quit() {
        if (tlContext.get() != null) tlContext.get().close();
        if (tlBrowser.get() != null) tlBrowser.get().close();
        if (tlPlaywright.get() != null) tlPlaywright.get().close();
        tlPage.remove(); tlContext.remove(); tlBrowser.remove(); tlPlaywright.remove();
    }
}
