package steam.test;

import framework.BaseTest;
import framework.PropertyReader;
import org.testng.annotations.Test;
import steam.menu.MainMenu;
import steam.pages.*;

import static framework.PropertyReader.seleniumPropertyPath;
import static steam.pages.HomePage.languagePropertyPath;


public class SteamTest extends BaseTest {

    @Test(description = "Select action game and download Steam")
    public void steamDownloadCheck() {

        PropertyReader propertyReader = new PropertyReader();

        HomePage homePage = new HomePage();
        homePage.chooseLanguage(propertyReader.getExactProperty(seleniumPropertyPath, "language"));
        homePage.isCorrectPageOpened(propertyReader.getExactProperty(languagePropertyPath, "home_title"));

        MainMenu mainMenu = new MainMenu();
        mainMenu.mainMenuNavigation(propertyReader.getExactProperty(languagePropertyPath, "main_menu_nav"),
                propertyReader.getExactProperty(languagePropertyPath, "main_menu_nav_subsection"));

        ActionPage actionPage = new ActionPage();
        actionPage.isCorrectPageOpened(propertyReader.getExactProperty(languagePropertyPath, "action_title"));
        actionPage.filterClick(propertyReader.getExactProperty(languagePropertyPath, "filter_by_discount"));
        actionPage.gameClick();

        AgePage checkAge = new AgePage();
        if (checkAge.isAgeCheckPageOpened()) {
            checkAge.ageCheckActions(propertyReader.getExactProperty(seleniumPropertyPath, "input_day"),
                    propertyReader.getExactProperty(seleniumPropertyPath, "input_month"),
                    propertyReader.getExactProperty(seleniumPropertyPath, "input_age"),
                    propertyReader.getExactProperty(languagePropertyPath,"check_age_btn"));
        }

        GamePage gamePage = new GamePage();
        gamePage.isCorrectPageOpened(gamePage.getCurrentName());
        gamePage.installSteamBtnClick(propertyReader.getExactProperty(languagePropertyPath, "install_steam"));

        SteamDownloadPage steamInstall = new SteamDownloadPage();
        steamInstall.isCorrectPageOpened(propertyReader.getExactProperty(languagePropertyPath, "steam_title"));
        steamInstall.downloadSteam(propertyReader.getExactProperty(languagePropertyPath, "download_steam"));
    }
}
