package framework.elements;

import framework.BaseEntity;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseElement extends BaseEntity {

    By locator;
    WebElement element;

    WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(configProperties.getProperty("implicit_wait")));

    public BaseElement(By locator) {
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
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(configProperties.getProperty("implicit_wait")), TimeUnit.SECONDS);
            return element.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(configProperties.getProperty("implicit_wait"))));
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
