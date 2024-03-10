package alas.utilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.util.concurrent.TimeUnit;

public class Driver {

    private Driver () {

    }

    private static WebDriver driver;

    public static WebDriver getDriver () {

        if(driver == null) {

            String browser = "";

            if(System.getProperty("BROWSER") == null) {

                browser = ConfigurationPropertiesReader.getProperty("browser");

            } else {

                browser = System.getProperty("BROWSER");

            }

            System.out.println("Browser: " + browser);

            switch (browser) {

                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    driver.manage().window().maximize();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    break;
                case "chrome-headless":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless=new");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(options);
                    break;
                case "firefox-headless":
                    FirefoxOptions options1 = new FirefoxOptions();
                    options1.addArguments("--headless=new");
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(options1);
                    break;
                case "ie":
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    driver.manage().window().maximize();
                    break;

                default:
                    throw new RuntimeException("Invalid browser type");

            }

        }

        return driver;

    }


    public static void closeDriver () {

        if (driver != null){

            driver.quit();
            driver = null;
        }

    }



}
