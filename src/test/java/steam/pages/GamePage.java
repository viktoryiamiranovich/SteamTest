package steam.pages;

import framework.BasePage;
import framework.elements.Button;
import org.openqa.selenium.By;

public class GamePage extends BasePage {


    public GamePage() {
        super(By.xpath("//div[@id='appHubAppName']"),"Game Page");
    }


    public void installSteamBtnClick(){
        Button installSteamBtn = new Button(By.xpath(String.format("//a[@class='header_installsteam_btn_content']")));
        installSteamBtn.click();
    }
}
