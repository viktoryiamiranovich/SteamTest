package steam.pages;

import framework.BaseEntity;
import framework.BasePage;
import framework.PropertyReader;
import framework.elements.Button;
import framework.elements.Dropdown;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class AgePage extends BaseEntity {

    Dropdown inputDayDropdown = new Dropdown(By.xpath("//select[@name='ageDay']"));
    Dropdown inputMonthDropdown = new Dropdown(By.xpath("//select[@name='ageMonth']"));
    Dropdown inputYearDropdown = new Dropdown(By.xpath("//select[@name='ageYear']"));
    Button btnViewPage = new Button(By.id("view_product_page_btn"));


    public boolean isAgeCheckPageOpened() {
        try {
            return inputDayDropdown.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void ageCheckActions() {
        inputDayDropdown.select(configProperties.getProperty("input_day"));
        inputMonthDropdown.select(configProperties.getProperty("input_month"));
        inputYearDropdown.select(configProperties.getProperty("input_year"));
        btnViewPage.click();
    }

}
