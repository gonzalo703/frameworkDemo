package org.Gonzalo.Manzano.TestComponents;

import org.Gonzalo.Manzano.AbstractObjects.AbstractComponent;
import org.Gonzalo.Manzano.pageObjects.LandingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public LandingPage landingPage;


    public void initializeDriver() throws IOException {

        // Class properties
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\org\\Gonzalo" +
                "\\Manzano\\Resources\\GlobalData.properties");
        prop.load(fis);

        String browserName = prop.getProperty("browser");
        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }
}
