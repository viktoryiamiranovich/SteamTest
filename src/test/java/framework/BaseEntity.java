package framework;

import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import static framework.BrowserFactory.createDriver;


public class BaseEntity {

    protected static WebDriver driver;
    protected static PropertyReader configProperties;
    protected static PropertyReader languageProperties;

    public void setup(String lang) {
        initProperties(lang);
        driver = createDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(configProperties.getProperty("implicit_wait")), TimeUnit.SECONDS);
        driver.get(configProperties.getProperty("websiteURL"));
    }

    public static void initProperties(String lang) {
        configProperties = new PropertyReader("config");
        languageProperties = new PropertyReader(lang);
    }


    protected void tearDown() {
        if (driver != null)
            driver.quit();
    }

}
