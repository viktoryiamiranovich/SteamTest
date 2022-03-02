package framework;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import steam.pages.SteamDownloadPage;

public abstract class BaseTest extends Browser {

    @BeforeTest (description = "removing file")
    public void trashClean(){
        SteamDownloadPage steamUninstall = new SteamDownloadPage();
        steamUninstall.deleteSteamBeforeTest(steamUninstall.isFilePresent());
    }

    @BeforeTest (description = "opening browser")
    public void driverStart(){
        setup();
    }

    @AfterTest
    public void testCompletion(){
        driverClose();
    }
}
