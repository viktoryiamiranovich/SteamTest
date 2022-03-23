package steam.menu;

import framework.BaseEntity;
import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;


public class LangMenu extends BaseEntity {
    private enum Language {
        RU("russian"),
        EN("english");

        private String language;

        Language(String lang) {
            this.language = lang;
        }
    }

    private Button btnLanguage = new Button(By.xpath("//span[@id='language_pulldown']"));
    private String languageLocator = "//div[@id='language_dropdown']//a[contains(@href, '%s')]";
    private Label lblLanguage;
    private static String propertyLanguage;
    private static String pageLanguage;


    public LangMenu() {
    }

    public void chooseLanguage() {
        btnLanguage.waitUntilPresent();
        propertyLanguage = languageProperties.getProperty("language");
        pageLanguage = btnLanguage.getText();

        if (!propertyLanguage.equals(pageLanguage)) {
            btnLanguage.click();
            lblLanguage = new Label(By.xpath((String.format(languageLocator, Language.valueOf(System.getProperty("language").toUpperCase()).language))));
            lblLanguage.clickAndWait();
        }
    }
}