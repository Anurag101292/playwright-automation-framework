package com.framework.factory;

import com.microsoft.playwright.Page;
public interface BrowserStrategy {
    Page createDriver(boolean headless);
}








