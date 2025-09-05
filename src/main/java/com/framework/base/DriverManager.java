package com.framework.base;

import com.microsoft.playwright.*;
public class DriverManager {
    private static ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static ThreadLocal<Page> page = new ThreadLocal<>();

    public static void initBrowser(String browserName) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext ctx = browser.newContext();
        Page pg = ctx.newPage();
        context.set(ctx);
        page.set(pg);
    }

    public static Page getPage() {
        return page.get();
    }

    public static void close() {
        context.get().close();
    }
}
