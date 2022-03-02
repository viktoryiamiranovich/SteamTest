package framework;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Function;

import static framework.PropertyReader.seleniumPropertyPath;

public abstract class BaseEntity {
    PropertyReader propertyReader = new PropertyReader();
    public WebDriver driver = Browser.driver;
    public abstract void isCorrectPageOpened(String currentValue);
    WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(propertyReader.getExactProperty(seleniumPropertyPath, "explicit_wait")));

    public void waitUntilExpectedConditions(Function expectedConditions){
        wait.until(expectedConditions);
    }

    public String getTitle(){
        return driver.getTitle();
    }


    public void changeTab(){
        for (String windowHandle : driver.getWindowHandles()) {
            if(!driver.getWindowHandle().contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }
}
