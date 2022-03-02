package steam.pages;

import org.openqa.selenium.By;
import org.testng.Assert;
import framework.BaseEntity;
import framework.elements.Label;
import java.util.*;



public class ActionPage extends BaseEntity {

    public static String gameName;
    Random rand = new Random();
    String gameNameLink = "//div[@class='salepreviewwidgets_SaleItemBrowserRow_gASJ2'][%s]//div[@class='salepreviewwidgets_StoreSaleWidgetTitle_2ekpT']";
    Label recommendedSpecials = new Label(By.xpath("//div[@class='facetedbrowse_FacetedBrowseItems_3EdZT']//div[@class='salepreviewwidgets_StoreSaleDiscountBox_cnkoF']"));
    String gameFilter = "//div[@class='saleitembrowser_SaleItemBrowserHeader_Eh-ow Panel Focusable']//div[@class='saleitembrowser_FlavorLabel_KDLAS Focusable'][contains(text(),'%s')]";
    String gameLink = "//div[@class='salepreviewwidgets_SaleItemBrowserRow_gASJ2'][%s]//div[contains(@class, 'StoreSaleWidgetTitle')]";
    Label gameTable = new Label(By.id("SaleSection_13268"));

    @Override
    public void isCorrectPageOpened(String currentTitle) {
        Assert.assertEquals(getTitle(), currentTitle);
    }

    public void filterClick(String filter) {
        Label filterSelect = new Label(By.xpath(String.format(gameFilter, filter)));
        filterSelect.clickAndWait();
        gameTable.scrollToElement();
    }


    public int lookingForGameWithBiggestSale(){

        int maxSale = 0;
        ArrayList<Integer> myIndList = new ArrayList();
        for (int i = 0; i < recommendedSpecials.getElementList().size(); i++){
            if(Integer.parseInt(recommendedSpecials.getElementList().get(i).getText().replace("%", "")) < maxSale){
                maxSale = Integer.parseInt(recommendedSpecials.getElementList().get(i).getText().replace("%", ""));
            }
        }
        for (int j = 0; j < recommendedSpecials.getElementList().size(); j++){
            if(Integer.parseInt(recommendedSpecials.getElementList().get(j).getText().replace("%", "")) == maxSale){
                myIndList.add(j+1);
            }
        }

        int indDiscount = myIndList.get(rand.nextInt(myIndList.size()));
        gameName = driver.findElement(By.xpath(String.format(gameNameLink,indDiscount))).getText();

        return indDiscount;
    }

    public void gameClick() {
        Label gameSelect = new Label(By.xpath(String.format(gameLink, lookingForGameWithBiggestSale())));
        gameSelect.click();
        changeTab();
    }

}