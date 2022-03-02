package framework.elements;

import framework.Browser;
import framework.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseElement {
    WebDriver driver = Browser.driver;
    WebDriverWait wait = new WebDriverWait(driver, 5);
    By locator;
    WebElement element;
    List<WebElement> elementList;
    PropertyReader propertyReader = new PropertyReader();

    public BaseElement(By locator){
        this.locator = locator;
    }

    public boolean isDisplayed(){
        return driver.findElement(locator).isDisplayed();
    }

    public void click() {
        waitUntilPresent();
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", element);
        }
        element.click();
    }

    public void clickAndWait() {
        click();
        waitForPageToLoad();
    }

    public void scrollToElement() {
        waitUntilPresent();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    public List<WebElement> getElementList(){
        if (arePresent()) return elementList;
        else return null;
    }

    public WebElement getElement(){
        waitUntilPresent();
        return element;
    }

    public void moveTo(){
        Actions actions = new Actions(driver);
        waitUntilPresent();
        actions.moveToElement(getElement()).build().perform();
    }

    public String getText(){
        return getElement().getText();
    }

    private boolean arePresent() {
        try {
            wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
                public Boolean apply(final WebDriver driver) {
                    try {
                        elementList = driver.findElements(locator);
                        for (WebElement el : elementList) {
                            if (el != null && el.isDisplayed()) {
                                element = el;
                                return element.isDisplayed();
                            }
                        }
                        element = driver.findElement(locator);
                    } catch (Exception e) {
                        return false;
                    }
                    return element.isDisplayed();
                }
            });
        } catch (Exception e) {
            return false;
        }
        try {
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(propertyReader.getExactProperty(PropertyReader.seleniumPropertyPath, "implicit_wait")), TimeUnit.SECONDS);
            return element.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isPresent() {
        try {
            element = driver.findElement(locator);
            return element.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean waitUntilPresent() {
        wait.until((ExpectedCondition<Boolean>) (x) -> {
            try {
                return isPresent();
            }catch (Exception e){
                return false;
            }
        });
        try {
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(propertyReader.getExactProperty(PropertyReader.seleniumPropertyPath, "implicit_wait")), TimeUnit.SECONDS);
            return element.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(propertyReader.getExactProperty(PropertyReader.seleniumPropertyPath, "implicit_wait"))));
        try {
            wait.until((ExpectedCondition<Boolean>) d -> {
                if (!(d instanceof JavascriptExecutor)) {
                    return true;
                }
                Object result = ((JavascriptExecutor) d)
                        .executeScript("return document['readyState'] ? 'complete' == document.readyState : true");
                return result instanceof Boolean && (Boolean) result;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
