package steam.menu;

import framework.elements.Dropdown;
import framework.elements.Label;
import org.openqa.selenium.By;

public class MainMenu {
    String mainNavLabelXpath = "//a[@class='pulldown_desktop'][contains(text(), '%s')]";
    String subSectionLabelXpath = "//a[@class='popup_menu_item'][contains(text(),'%s')]";

    public void mainMenuNavigation(String mainLabelText, String subsectionText){
        Label mainNavLabel = new Label(By.xpath(String.format(mainNavLabelXpath, mainLabelText)));
        Dropdown subsectionDropdown = new Dropdown(By.xpath(String.format(subSectionLabelXpath, subsectionText)));
        mainNavLabel.moveTo();
        subsectionDropdown.click();
    }
}
