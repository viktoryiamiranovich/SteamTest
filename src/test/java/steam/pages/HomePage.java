package steam.pages;

import framework.BasePage;
import org.openqa.selenium.By;
import steam.menu.LangMenu;
import steam.menu.MainMenu;

public class HomePage extends BasePage {

    public LangMenu languageMenu = new LangMenu();
    public MainMenu gamesMenu = new MainMenu();

    public HomePage() {
        super(By.xpath("//div[@id='content_login']"), "Main Page");
    }

}
