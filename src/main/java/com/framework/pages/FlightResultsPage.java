package com.framework.pages;
import com.microsoft.playwright.Page;
import java.util.List;
public class FlightResultsPage {
    private final Page page; public FlightResultsPage(Page page){ this.page = page; }
    public List<String> getFlightTitles(){ return page.locator(".flight-card .title").allTextContents(); }
    public void pickFirstFlight(){ page.click("(//button[contains(text(),'Book')])[1]"); }
}
