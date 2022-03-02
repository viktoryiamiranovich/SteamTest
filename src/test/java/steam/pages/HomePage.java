package steam.pages;

import framework.BaseEntity;
import framework.PropertyReader;
import framework.elements.Button;
import framework.elements.Dropdown;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.HashMap;

public class HomePage extends BaseEntity {
    PropertyReader propertyReader = new PropertyReader();

    public static String languagePropertyPath;
    HashMap<String, String> langMap = new HashMap<>();
    Button openLanguageListBtn = new Button(By.xpath("//span[@id='language_pulldown']"));
    Dropdown languageList = new Dropdown(By.xpath("//a[@class='popup_menu_item tight']"));

    public HashMap<String,String> langList(){
        langMap.put("Русский", "src/test/resources/ru_localization.properties");
        langMap.put("English", "src/test/resources/en_localization.properties");
        return langMap;
    }

    public void chooseLanguage(String lang){
        Dropdown selectLanguageDropdown = new Dropdown(By.xpath(String.format("//a[@class='popup_menu_item tight'][contains(text(), '%s')]", lang)));
        openLanguageListBtn.click();
        if (searchChosenLanguage(lang) && isLanguageProvided(lang))
        {
            languagePropertyPath = langList().get(lang);
            selectLanguageDropdown.click();
        }
        else {
            languagePropertyPath = langList().get(lang);
            openLanguageListBtn.click();
        }
    }

    public boolean isLanguageProvided(String lang){
        return langList().containsKey(lang);
    }

    public boolean searchChosenLanguage(String lang){
        boolean bool = false;
        for(WebElement element : languageList.getElementList()){
            if (element.getText().startsWith(lang)){
                bool = true;
                break;
            }
        }
        return bool;
    }

    @Override
    public void isCorrectPageOpened(String currentTitle) {
        waitUntilExpectedConditions(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format("//a[@class='pulldown_desktop'][contains(text(), '%s')]",
                propertyReader.getExactProperty(languagePropertyPath, "main_menu_nav")))));
        Assert.assertEquals(getTitle(), currentTitle);
    }

}
