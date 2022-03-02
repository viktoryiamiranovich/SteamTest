package steam.pages;

import framework.BaseEntity;
import framework.PropertyReader;
import framework.elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.io.File;

import static framework.PropertyReader.seleniumPropertyPath;


public class SteamDownloadPage extends BaseEntity {

    PropertyReader propertyReader = new PropertyReader();
    File steamFile = new File(System.getProperty("user.dir") + propertyReader.getExactProperty(seleniumPropertyPath, "steam_save_dir")
            + (propertyReader.getExactProperty(seleniumPropertyPath, "app_name")));

    @Override
    public void isCorrectPageOpened(String currentValue) {
        Assert.assertEquals(getTitle(), currentValue);
    }


    public boolean waitForFileDownload(final File file) {
        return (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return file.canRead();
            }
        });
    }

    public void downloadSteam(String downloadSteamBtnText){
        Button downloadSteamBtn = new Button(By.xpath(String.format("//a[text()='%s']", downloadSteamBtnText)));
        downloadSteamBtn.click();
        this.waitForFileDownload(steamFile);
    }

    public boolean isFilePresent(){
        return steamFile.exists();
    }

    public void deleteSteamBeforeTest(boolean bool){
        if(bool){
            steamFile.delete();
        }
    }
}
