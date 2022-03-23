package framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class Dropdown extends BaseElement{

    String type;

    public Dropdown(By locator) {
        super(locator);
        type = "dropdown";
    }

    public String getType() {
        return this.type;
    }

    public void select(String value){
        Select select = new Select(getElement());
        select.selectByValue(value);
    }

}
