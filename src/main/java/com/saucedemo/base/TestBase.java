package com.saucedemo.base;

import java.time.Duration;


import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import com.saucedemo.Browser.Browsers;
import com.saucedemo.Listeners.WebdriverEvents;

import io.github.bonigarcia.wdm.WebDriverManager;

@SuppressWarnings("deprecation")
public class TestBase {
    public static WebDriver driver;
    private Browsers BROWSER;
    private final String URL = "https://www.saucedemo.com/";
    public static Logger logger;
    protected WebDriverWait wait;
    public WebdriverEvents events;
    public EventFiringWebDriver eDriver;
    


    public void initialise() {
        setBrowser();
        initializeWebDriver();
        // Maximize Window
        driver.manage().window().maximize();
        // Manage Timeout
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        // Load Webpage
        driver.get(URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    @BeforeClass
    public void setUpLogger() {
        logger = Logger.getLogger(TestBase.class);
        PropertyConfigurator.configure("log4j.properties");
        BasicConfigurator.configure();
        logger.setLevel(Level.ALL);
    }


    private void setBrowser() {
        String browser = System.getProperty("browser", "CHROME").toUpperCase();
        try {
            BROWSER = Browsers.valueOf(browser);
        } catch (IllegalArgumentException e) {
            throw new InvalidArgumentException("Pass Correct Browser name");
        }
    }

    private void initializeWebDriver() {
        switch (BROWSER) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case SAFARI:
                driver = new SafariDriver();
                break;
            default:
                throw new InvalidArgumentException("Pass Correct Browser name");
        }
        eDriver = new EventFiringWebDriver(driver);
        events = new WebdriverEvents();
        eDriver.register(events);
        driver = eDriver;
    }

    // Method to close the browser
    public void tearDown() {
            driver.quit();
        
    }
    
    public void selectDropdownByValueOrText(WebElement element, String value, String text) {
        Select select = new Select(element);
        try {
            select.selectByValue(value);
        } catch (Exception e) {
            select.selectByVisibleText(text);
        }
    }
}




