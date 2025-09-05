package com.tests;

import org.testng.annotations.*;
import com.framework.base.DriverManager;
import com.framework.pages.SearchPage;

public class FlightBookingTests {
    @BeforeMethod
    public void setUp() {
        DriverManager.initBrowser("chromium");
    }

    @Test
    public void testSearchFlight() {
        SearchPage search = new SearchPage(DriverManager.getPage());
        search.searchFlight("Bangalore", "London", "2025-12-01");
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.close();
    }
}
