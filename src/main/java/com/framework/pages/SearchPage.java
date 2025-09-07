package com.framework.pages;
import com.microsoft.playwright.Page;
public class SearchPage {
    private final Page page;
    public SearchPage(Page page) { this.page = page; }
    public void searchFlight(String from, String to, String date) {
        page.fill("#fromCity", from); page.fill("#toCity", to); page.fill("#departDate", date); page.click("#searchBtn");
    }
    public void searchHotel(String city, String checkin, String checkout) {
        page.fill("#hotelCity", city); page.fill("#checkin", checkin); page.fill("#checkout", checkout); page.click("#searchHotelsBtn");
    }
}
