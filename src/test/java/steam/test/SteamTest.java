package steam.test;

import framework.BaseTest;
import org.testng.annotations.Test;
import steam.menu.MainMenu;
import steam.pages.*;

public class SteamTest extends BaseTest {

    @Test(description = "Select action game and download Steam")
    public void steamDownloadCheck() {

        log.info("Open Steam");
        HomePage homePage = new HomePage();
        log.info("Select language");
        homePage.languageMenu.chooseLanguage();
        log.info("Go to Action page");
        homePage.gamesMenu.navigateMenu(MainMenu.Menu.CATEGORY, MainMenu.Games.ACTION);

        ActionPage actionPage = new ActionPage();
        log.info("Looking for game with maximum discount");
        actionPage.lookingForGameWithBiggestSale();
        log.info("Open game page with maximum discount");

        AgePage checkAge = new AgePage();
        if (checkAge.isAgeCheckPageOpened()) {
            log.info("Check age");
            checkAge.ageCheckActions();
        }

        GamePage gamePage = new GamePage();
        log.info("Go to the Download Steam page");
        gamePage.installSteamBtnClick();

        SteamDownloadPage steamDownload = new SteamDownloadPage();
        log.info("Download Steam");
        steamDownload.downloadSteam();
    }
}
