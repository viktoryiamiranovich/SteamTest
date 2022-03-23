package steam.test;

import framework.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import steam.menu.MainMenu;
import steam.pages.*;

public class SteamTest extends BaseTest {

    @Parameters({ "Language" }) // specified in the testng.xml file
    @Test(description = "Select action game and download Steam")
    public void steamDownloadCheck(String language) {

        HomePage homePage = new HomePage();
        homePage.languageMenu.chooseLanguage();
        homePage.gamesMenu.navigateMenu(MainMenu.Menu.CATEGORY, MainMenu.Games.ACTION);

        ActionPage actionPage = new ActionPage();
        actionPage.lookingForGameWithBiggestSale();

        AgePage checkAge = new AgePage();
        if (checkAge.isAgeCheckPageOpened()) {
            checkAge.ageCheckActions();
        }

        GamePage gamePage = new GamePage();
        gamePage.installSteamBtnClick();

        SteamDownloadPage steamDownload = new SteamDownloadPage();
        steamDownload.downloadSteam();
    }
}
