package steam.pages;

import framework.BasePage;
import framework.elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;

public class SteamDownloadPage extends BasePage {

    private static String winFileName="SteamSetup.exe";
    private static String linuxFileName="steam_latest.deb";
    private static String macFileName="steam.dmg";
    private static File tempDir;
    private static String installFile;
    private static String tempFolder = configProperties.getProperty("tempFolder");
    private File fileToCheck;

    static {
        try {
            tempDir = new File(tempFolder).getCanonicalFile();
        } catch (IOException e) {
            System.out.println("Could not get canonical path");
        }
    }

    public SteamDownloadPage() {
        super(By.xpath("//div[@id='global_header']"),"Steam Download Page");
    }

    public boolean waitForFileDownload(final File file) {
        return (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return file.canRead();
            }
        });
    }

    public void downloadSteam(){
        Button downloadSteamBtn = new Button(By.xpath(String.format("//a[@class='about_install_steam_link']")));
        downloadSteamBtn.click();
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            installFile=winFileName;
        } else if (System.getProperty("os.name").toLowerCase().contains("linux")){
            installFile=linuxFileName;
        } else {
            installFile=macFileName;
        }
        fileToCheck = new File(tempDir+"/"+installFile);//tempDir.listFiles()[0];
        this.waitForFileDownload(fileToCheck);
    }

    public static void deleteTempFiles() {
        String dirPath = null;
        try {
            dirPath = new File(tempFolder).getCanonicalPath();
        } catch (IOException e) {
            System.out.println("Could not get canonical path");
        }

        File dir = new File(dirPath);
        for (File f : dir.listFiles()) {
            f.delete();
        }
    }

}
