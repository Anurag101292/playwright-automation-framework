package com.framework.decorators;
import com.framework.utils.ScreenshotUtil; import com.microsoft.playwright.Page;
public class ScreenshotDecorator {
    public static void run(Page page, Runnable action, String name) {
        try { action.run(); } catch(Exception e) { ScreenshotUtil.capture(page, name); throw e; }
    }
}
