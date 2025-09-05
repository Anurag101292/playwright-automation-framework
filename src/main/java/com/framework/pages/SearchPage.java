package com.framework.pages;

import com.microsoft.playwright.Page;

public class SearchPage {
    private Page page;
    public SearchPage(Page page) {
        this.page = page;
    }
    public void searchFlight(String from, String to, String date) {
        // Example locator usage
        page.fill("#fromCity", from);
        page.fill("#toCity", to);
        page.fill("#departDate", date);
        page.click("#searchBtn");
    }
}
