package framework;

import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;
import static framework.BrowserFactory.createDriver;
import static framework.PropertyReader.seleniumPropertyPath;

public class Browser{
    PropertyReader propertyReader = new PropertyReader();

    public static WebDriver driver;

    public void setup(){
        driver = createDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(propertyReader.getExactProperty(seleniumPropertyPath, "implicit_wait")), TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(propertyReader.getExactProperty(seleniumPropertyPath, "implicit_wait")), TimeUnit.SECONDS);
        driver.get(propertyReader.getExactProperty(seleniumPropertyPath, "websiteURL"));
    }

    public void get(String url){
        driver.get(url);
    }


    public void driverClose()
    {
        driver.quit();
    }
}
