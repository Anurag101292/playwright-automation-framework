# Playwright Automation Framework (Java + TestNG)

playwright-automation-framework/
│── pom.xml                         # Maven build file
│── Dockerfile                      # Docker container definition
│── Jenkinsfile                     # Jenkins pipeline config
│── README.md                       # Project documentation
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.framework
│   │   │       ├── base
│   │   │       │   ├── TestBase.java            # Test initialization
│   │   │       │   ├── DriverManager.java       # Singleton + ThreadLocal
│   │   │       │   ├── BrowserFactory.java      # Abstract Factory
│   │   │       │   └── ConfigManager.java       # Reads env configs
│   │   │       │
│   │   │       ├── pages                       # Page Object Model
│   │   │       │   ├── SearchPage.java
│   │   │       │   ├── FlightResultsPage.java
│   │   │       │   ├── HotelResultsPage.java
│   │   │       │   ├── PassengerDetailsPage.java
│   │   │       │   └── PaymentPage.java
│   │   │       │
│   │   │       ├── utils                       # Utilities (Decorator, helpers)
│   │   │       │   ├── LoggerDecorator.java     # Decorator for logging
│   │   │       │   ├── RetryHandler.java        # Retry mechanism
│   │   │       │   ├── ScreenshotUtil.java
│   │   │       │   ├── ExcelUtil.java
│   │   │       │   ├── JsonUtil.java
│   │   │       │   ├── MailUtil.java
│   │   │       │   ├── PropertyUtil.java
│   │   │       │   └── S3Uploader.java          # Upload reports to S3
│   │   │       │
│   │   │       ├── mocks
│   │   │       │   ├── MockServer.java          # Playwright route() setup
│   │   │       │   └── FlightMockData.json      # Mocked flight data
│   │   │       │
│   │   │       └── reporting
│   │   │           ├── AllureManager.java
│   │   │           └── LogManager.java
│   │   │
│   │   └── resources
│   │       ├── config
│   │       │   ├── config-SIT.properties
│   │       │   ├── config-UAT.properties
│   │       │   ├── config-PROD.properties
│   │       │   └── log4j2.xml
│   │       │
│   │       ├── locators
│   │       │   ├── searchPage.json
│   │       │   ├── flightResultsPage.json
│   │       │   ├── hotelResultsPage.json
│   │       │   ├── passengerPage.json
│   │       │   └── paymentPage.json
│   │       │
│   │       └── testdata
│   │           ├── passengers.xlsx
│   │           └── payments.xlsx
│   │
│   └── test
│       └── java
│           └── com.tests
│               ├── SearchTests.java
│               ├── FlightBookingTests.java
│               ├── HotelBookingTests.java
│               ├── PassengerDetailsTests.java
│               └── PaymentTests.java
│
└── target/                           # Build outputs (Allure reports, logs, screenshots)



## 📌 Overview
This is a Playwright automation framework built with **Java 17**, **TestNG**, and **Maven**.
It supports:
- Parallel execution (via TestNG + ThreadLocal)
- Page Object Model (POM)
- Mocking with Playwright route API
- Allure Reporting
- Log4j Logging
- AWS S3 report uploads
- Docker + Jenkins integration

## 🚀 How to Run Locally
```bash
mvn clean test -P SIT -DenableMocks=true -Dthreads=10
```

## 🐳 Run with Docker
```bash
docker build -t playwright-automation .
docker run --rm -e ENV=SIT -e ENABLE_MOCKS=true -e THREADS=10 playwright-automation
```

## 📊 Reports
- Reports are generated in `target/allure-results`
- Generate locally:
```bash
mvn allure:report
```
- View report:
```bash
allure serve target/allure-results
```

## ☁️ AWS S3 Upload
The framework uploads reports to S3 via `S3Uploader` utility.

## 🔧 Framework Layers
- `base/` → DriverManager (Singleton, ThreadLocal), ConfigManager
- `pages/` → Page Object Model (Search, FlightResults, Payment)
- `utils/` → Logger, Retry, Screenshot, ExcelUtil, JsonUtil, MailUtil
- `mocks/` → MockServer + FlightMockData.json
- `reporting/` → AllureManager, LogManager
- `resources/` → config properties, locator JSONs, test data
- `tests/` → TestNG test classes

## 🔀 Parallel Execution
Define threads using:
```bash
-Dthreads=10
```

## 🧩 Mocking vs Normal Execution
- Enable mocks: `-DenableMocks=true`
- Disable mocks: `-DenableMocks=false`
