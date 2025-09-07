package com.framework.base;
import com.framework.mocks.MockingUtil;
import org.testng.annotations.*;
public class TestBase {
    @BeforeSuite
    @Parameters({"ENV"})
    public void beforeSuite(@Optional("SIT") String env) { ConfigManager.load(env); }
    @BeforeMethod
    @Parameters({"browser","headless","ENABLE_MOCKS"})
    public void setup(@Optional("chromium") String browser, @Optional("true") String headless, @Optional("true") String enableMocks) {
        boolean head = Boolean.parseBoolean(System.getProperty("headless", headless));
        DriverManager.init(browser, head);
        if (Boolean.parseBoolean(System.getProperty("enableMocks", enableMocks))) {
            MockingUtil.applyMocks(DriverManager.getPage(), "src/main/resources/mocks/mock-config.json");
        }
    }
    @AfterMethod public void tearDown() { DriverManager.quit(); }
}
