package com.framework.base;

import com.framework.factory.BrowserStrategy;
import com.framework.factory.BrowserFactory;
import com.microsoft.playwright.Page;

public class DriverManager {

    // Thread-safe Page for parallel execution in TestNG
    private static ThreadLocal<Page> pageThreadLocal = new ThreadLocal<>();

    /**
     * Initialize browser using Strategy + Factory pattern
     * Called from TestBase @BeforeMethod
     */
    public static void init(String browserName, boolean headless) {
        BrowserStrategy strategy = BrowserFactory.getStrategy(browserName);
        Page page = strategy.createDriver(headless);
        pageThreadLocal.set(page);
        System.out.println(">>> [DriverManager] Initialized browser: " + browserName + " | Headless: " + headless);
    }

    /**
     * Get the Page instance for the current test thread
     */
    public static Page getPage() {
        return pageThreadLocal.get();
    }

    /**
     * Cleanup browser after each test
     */
    public static void quit() {
        Page page = pageThreadLocal.get();
        if (page != null) {
            page.context().browser().close();
            pageThreadLocal.remove();
            System.out.println(">>> [DriverManager] Browser closed and cleaned up.");
        }
    }
}
