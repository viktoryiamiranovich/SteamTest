package steam.pages;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.*;

public class ActionPage extends BasePage {

    Random rand = new Random();
    public static String lblWithDiscount = "//div[contains(@class, 'discount_block  discount_block_inline')]/div[contains(@class, 'discount_pct')]";
    public static String gameWithMAXDiscount = "//div[contains(@class, 'discount_block  discount_block_inline')]/div[@class = 'discount_pct' and contains(text(), '%s')]";


    public ActionPage() {
        super(By.xpath("//div[@class='contenthub_specials_ctn']"),"Action Page");
    }

    public void lookingForGameWithBiggestSale() {
        List<WebElement> discounts = driver.findElements(By.xpath(lblWithDiscount));
        List<String> list = new ArrayList<>();
        for (WebElement discount : discounts) {
            list.add(discount.getText());
        }
        String maxDiscount = Collections.max(list);
        List<WebElement> gamesWithMaxDiscount = driver.findElements(By.xpath(String.format(gameWithMAXDiscount, maxDiscount)));
        int indDiscount = rand.nextInt(gamesWithMaxDiscount.size());
        gamesWithMaxDiscount.get(indDiscount).click();
    }

}