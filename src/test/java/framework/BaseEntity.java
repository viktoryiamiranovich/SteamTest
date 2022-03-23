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

    public void setup() {
        initProperties();
        driver = createDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(configProperties.getProperty("implicit_wait")), TimeUnit.SECONDS);
        driver.get(configProperties.getProperty("websiteURL"));
        deleteTempFiles();
    }

    public static void initProperties() {
        configProperties = new PropertyReader("config");
        languageProperties = new PropertyReader(configProperties.getProperty("language"));
    }

    public static void deleteTempFiles() {
        String dirPath = null;
        try {
            dirPath = new File(System.getProperty("user.dir")
                    + configProperties.getProperty("tempFolder")).getCanonicalPath();
        } catch (IOException e) {
            System.out.println("Could not get canonical path");
        }

        File dir = new File(dirPath);
        for (File f : dir.listFiles()) {
            System.out.println(String.valueOf(f));
                f.delete();
        }
    }

    protected void tearDown() {
        if (driver != null)
            driver.quit();
    }

}
