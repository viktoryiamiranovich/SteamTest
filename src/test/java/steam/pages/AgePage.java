package steam.pages;

import framework.elements.Button;
import framework.elements.Dropdown;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class AgePage {
    Dropdown inputDayDropdown = new Dropdown(By.xpath("//select[@name='ageDay']"));
    Dropdown inputMonthDropdown = new Dropdown(By.xpath("//select[@name='ageMonth']"));
    Dropdown inputAgeDropdown = new Dropdown(By.xpath("//select[@name='ageYear']"));

    public boolean isAgeCheckPageOpened() {
        try {
            return inputDayDropdown.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void ageCheckActions(String input_day, String input_month, String input_age, String openPageBtnText) {
        inputDayDropdown.select(input_day);
        inputMonthDropdown.select(input_month);
        inputAgeDropdown.select(input_age);
        Button openPageBtn = new Button(By.xpath(String.format("//span[contains(text(), '%s')]", openPageBtnText)));
        openPageBtn.click();
    }

}
