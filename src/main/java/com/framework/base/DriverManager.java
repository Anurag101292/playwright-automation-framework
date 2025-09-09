package com.framework.base;

import com.microsoft.playwright.*;

public class DriverManager {

    private static ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static ThreadLocal<Page> page = new ThreadLocal<>();

    // init driver
    public static void init(String browserName, boolean headless) {
        playwright.set(Playwright.create());

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setHeadless(headless);

        switch (browserName.toLowerCase()) {
            case "chromium":
                browser.set(playwright.get().chromium().launch(options));
                break;
            case "firefox":
                browser.set(playwright.get().firefox().launch(options));
                break;
            case "webkit":
                browser.set(playwright.get().webkit().launch(options));
                break;
            default:
                throw new RuntimeException("Unsupported browser: " + browserName);
        }

        context.set(browser.get().newContext());
        page.set(context.get().newPage());
    }

    public static Page getPage() {
        return page.get();
    }

    public static BrowserContext getContext() {
        return context.get();
    }

    public static Browser getBrowser() {
        return browser.get();
    }

    // ✅ quit / close everything
    public static void quit() {
        try {
            if (context.get() != null) {
                context.get().close();
                context.remove();
            }
            if (browser.get() != null) {
                browser.get().close();
                browser.remove();
            }
            if (playwright.get() != null) {
                playwright.get().close();
                playwright.remove();
            }
            page.remove();
        } catch (Exception e) {
            System.err.println("⚠️ Error closing Playwright: " + e.getMessage());
        }
    }
}
