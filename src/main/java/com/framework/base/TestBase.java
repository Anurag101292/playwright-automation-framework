package com.framework.base;

import com.framework.mocks.MockingUtil;
import com.framework.utils.MailUtil;
import org.testng.annotations.*;

public class TestBase {

    @BeforeSuite
    @Parameters({"ENV"})
    public void beforeSuite(@Optional("PROD") String env) {
        System.out.println(">>> [BeforeSuite] Running for environment: " + env);
        ConfigManager.load(env);
    }

    @BeforeMethod
    @Parameters({"browser", "headless", "ENABLE_MOCKS"})
    public void setup(@Optional("chromium") String browser,
                      @Optional("true") String headless,
                      @Optional("false") String enableMocks) {

        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", headless));
        DriverManager.init(browser, isHeadless);

        // Navigate to base URL
        String baseUrl = ConfigManager.get("base.url");
        DriverManager.getPage().navigate(baseUrl);

        // Apply mocks if enabled
        if (Boolean.parseBoolean(System.getProperty("enableMocks", enableMocks))) {
            MockingUtil.applyMocks(DriverManager.getPage(), "src/main/resources/mocks/mock-config.json");
        }

        System.out.println(">>> [BeforeMethod] Test setup done.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quit();
        System.out.println(">>> [AfterMethod] Browser quit.");
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        String env = System.getProperty("env", "PROD");
       // MailUtil.sendReport(env);
        System.out.println(">>> [AfterSuite] Report sent for environment: " + env);
    }
}
