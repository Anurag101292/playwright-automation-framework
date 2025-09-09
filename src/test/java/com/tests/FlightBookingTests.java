package com.tests;

import com.framework.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.*;
import com.framework.base.DriverManager;

public class FlightBookingTests extends TestBase {

    @Test
    public void verifyAppLaunch() {
        String title = DriverManager.getPage().title();
        System.out.println("Page title: " + title);

        Assert.assertNotNull(title, "Title should not be null");
        Assert.assertFalse(title.isEmpty(), "Title should not be empty");
    }


}
