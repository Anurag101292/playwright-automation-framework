package com.framework.factory;
import com.microsoft.playwright.Page;
public interface BrowserFactory { Page createPage(boolean headless); }
