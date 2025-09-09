package com.framework.base;
import com.framework.mocks.MockingUtil;
import com.framework.utils.MailUtil;
import org.testng.annotations.*;

import javax.sound.midi.Soundbank;

public class TestBase {
    @BeforeSuite
    @Parameters({"ENV"})
    public void beforeSuite(@Optional("PROD") String env) {
        System.out.println(">>> [BeforeSuite] running...");
        System.out.println(">>> Environment: " + env);
        ConfigManager.load(env); }
    @BeforeMethod
    @Parameters({"browser","headless","ENABLE_MOCKS"})
    public void setup(@Optional("chromium") String browser,
                      @Optional("true") String headless,
                      @Optional("true") String enableMocks) {
        System.out.println(">>> [BeforeMethod] running...");
        boolean head = Boolean.parseBoolean(System.getProperty("headless", headless));
        DriverManager.init(browser, head);
        String baseUrl = ConfigManager.get("base.url");
        DriverManager.getPage().navigate(baseUrl);
        if (Boolean.parseBoolean(System.getProperty("enableMocks", enableMocks))) {
            MockingUtil.applyMocks(DriverManager.getPage(), "src/main/resources/mocks/mock-config.json");
        }
    }
    @AfterMethod public void tearDown() { DriverManager.quit(); }
    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        String env = System.getProperty("env", "PROD"); // default SIT
        ConfigManager.load(env);
        MailUtil.sendReport(env);
    }

}
