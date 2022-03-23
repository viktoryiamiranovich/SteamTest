package steam.menu;

import framework.BaseEntity;
import framework.elements.Label;
import org.openqa.selenium.By;

public class MainMenu extends BaseEntity {

    public enum Menu {
        CATEGORY("Categories");

        private String menuCategory;

        Menu(String category) {
            this.menuCategory = category;
        }
    }

    public enum Games {
        ACTION("Action");

        private String genre;

        Games(String genre) {
            this.genre = genre;
        }

    }

    private static String menuItemLocator = "//a[@class='pulldown_desktop'][contains(text(),'%s')]";
    private static String subSectionLabelXpath = "//div[@id='genre_flyout']//a[contains(text(),'%s')]";
    private Label lblMenu;
    private Label lblGame;

    public void navigateMenu(Menu category, Games games) {
        lblMenu = new Label(By.xpath(String.format(menuItemLocator, languageProperties.getProperty(category.menuCategory))));
        lblGame = new Label(By.xpath(String.format(subSectionLabelXpath, languageProperties.getProperty(games.genre))));
        lblMenu.moveTo();
        lblGame.click();
    }
}
