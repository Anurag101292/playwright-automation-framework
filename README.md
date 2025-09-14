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

👉 Interview one-liner:
"My framework uses Strategy + Factory for browser management, Singleton for driver lifecycle, POM for UI abstraction, and Observer (TestNG listeners) for reporting. This makes it modular, scalable, and cloud-ready."
📝 Framework Summary with Design Patterns

1. Framework Overview
Built using Playwright + TestNG + Maven.
Supports cross-browser execution (Chromium, Firefox, WebKit) and remote/cloud execution (e.g., Selenium Grid, BrowserStack).
Designed with scalability, maintainability, and reusability in mind.
Key focus: Test Automation Framework with SOLID principles.

2. Applied Design Patterns
✅ Strategy Pattern (Browser handling)
Where: BrowserStrategy, ChromiumStrategy, FirefoxStrategy, WebkitStrategy, RemoteStrategy.
Why: To encapsulate different browser initialization logics into separate strategy classes.
Benefit: Easy to extend (add new browser or cloud provider without touching existing code).
Interview Pitch: "This enables plug-and-play browser strategies. Teams can switch execution from local to remote by just changing config (-Dbrowser=remote). No code changes in tests or driver manager."

✅ Factory Pattern (Browser selection)
Where: BrowserFactory.
Why: To centralize and abstract object creation for different BrowserStrategy implementations.
Benefit: Simplifies client code (DriverManager just asks factory for strategy).
Interview Pitch: "The Factory ensures single responsibility — if I need to introduce a new strategy, I just update the factory, not the test logic."

✅ Singleton Pattern (Driver lifecycle)
Where: DriverManager.
Why: To ensure a single Playwright and Page instance per test thread.
Benefit: Prevents multiple browser instances from being created accidentally, optimizes resource usage.
Interview Pitch: "Using Singleton for driver management avoids flaky tests caused by duplicate browser instances."

✅ Page Object Model (POM)
Where: com.pages.* classes (e.g., LoginPage, FlightBookingPage).
Why: To separate test logic from page interactions.
Benefit: Reduces code duplication, improves readability, supports reusability.
Interview Pitch: "POM ensures that locators and actions are maintained in one place. If UI changes, only the page class needs an update, not every test."

✅ TestNG Listeners (Observer Pattern)
Where: ITestListener implementation (e.g., TestListener.java).
Why: To capture test events (onTestFailure, onTestSuccess, onStart, onFinish).
Benefit: Enables reporting, logging, screenshots on failure.

Interview Pitch: "I used Observer via TestNG listeners to decouple reporting from test execution. Tests remain clean while listeners handle logging and screenshots."
✅ Builder Pattern (Optional: Test Data / Config)
Where: If you have ConfigBuilder or TestDataBuilder.
Why: To construct complex test data objects/configurations in a readable way.
Benefit: Makes test data setup flexible and human-readable.

Interview Pitch: "Builder pattern helps me manage complex test data objects without huge constructors."
3. Execution Flow
Test starts → TestNG triggers @BeforeSuite/@BeforeMethod.
DriverManager.init() calls BrowserFactory.getStrategy().
BrowserFactory returns appropriate BrowserStrategy.
Strategy initializes the browser (local/remote).
Page Objects (LoginPage, FlightBookingPage) used inside tests.
Listeners capture results, logs, screenshots.

4. Why This Design Stands Out
✅ Open/Closed Principle (OCP): Add new browsers/providers without modifying core driver logic.
✅ Separation of Concerns: Driver setup, test execution, reporting, and page actions are decoupled.
✅ Scalable: Ready for local, grid, and cloud execution.
✅ Maintainable: Any change in UI or infrastructure requires minimal updates.


