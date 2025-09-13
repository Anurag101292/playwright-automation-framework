package com.framework.factory;

public interface BrowserFactory {

    public static BrowserStrategy getStrategy(String browserName) {
        if (browserName == null) {
            throw new IllegalArgumentException("Browser name cannot be null");
        }

        switch (browserName.toLowerCase()) {
            case "chromium":
            case "chrome":
                return new ChromiumStrategy();
            case "firefox":
                return new FirefoxStrategy();
            case "webkit":
            case "safari":
                return new WebkitStrategy();
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
    }












}
