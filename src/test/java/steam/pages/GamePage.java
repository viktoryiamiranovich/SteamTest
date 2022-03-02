package steam.pages;

import framework.BaseEntity;
import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;
import org.testng.Assert;

public class GamePage extends BaseEntity {

    Label currentGameName = new Label(By.xpath("//div[@id='appHubAppName']"));

    public String getCurrentName(){
        return currentGameName.getText();
    }

    @Override
    public void isCorrectPageOpened(String currentSale) {
        Assert.assertTrue(ActionPage.gameName.equalsIgnoreCase(currentSale));
    }

    public void installSteamBtnClick(String installSteamText){
        Button installSteamBtn = new Button(By.xpath((String.format("//a[contains(text(), '%s')]", installSteamText))));
        installSteamBtn.click();
    }
}
