package PageObjects;

import org.jsoup.Connection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShoppingCartPage extends BasePage {

    public String ProductNameLocator = "//table/tbody/tr/td/a[text()='<name>']";
    public By ProductQuantityLocator = By.xpath("//*[@id='content']/form/div/table/tbody/tr/td[4]/div/input");

    private String productRowLocator = "//div[@id='content']//tr[contains(.,'<name>')]";
    private WebElement productRow;
    private By inputRowSelector = By.cssSelector("input");
    private By imageSelector = By.cssSelector("img");
    private By shoppingCartRows = By.xpath("//div[@id='content']//div[contains(@class, 'table-responsive')]//tr");
    private By GetProductAddedInCartMessageLocator = By.xpath("//*[contains(@class, 'alert-success alert-dismissible')]");
    private By ProductNotAvailableMessageLocator = By.xpath("//*[contains(@class, 'alert-danger alert-dismissible')]");


    private By checkoutButtonLocator = By.xpath("//*[contains(@class,'btn btn-primary')]");

    public ShoppingCartPage(WebDriver _driver){
        super(_driver);
    }

    public boolean isProductNameDisplayed(String name){
        return driver.findElement(By.xpath(ProductNameLocator.replace("<name>", name))).isDisplayed();
    }
    public int getProductQuantity(){
        return Integer.parseInt(driver.findElement(ProductQuantityLocator).getAttribute("value"));
    }
    public boolean isProductRowDisplayed(String name){
        this.productRow =
                driver.findElement(
                        By.xpath(productRowLocator.replace("<name>", name)));
        return this.productRow.isDisplayed();
    }
    public int getProductRowQuantity(){
        String sAmount = productRow.findElement(inputRowSelector)
                .getAttribute("value");
        return Integer.parseInt(sAmount);
    }
    public String getProductImageURL(){
        String imageURL = productRow.findElement(imageSelector)
                .getAttribute("src");
        return imageURL;
    }
    public int getAmountOfShoppingCartRows(){
        return driver.findElements(shoppingCartRows).size() - 1;
    }

    //Proyecto
    public String getProductAddedInCartMessage(){
        return driver.findElement(GetProductAddedInCartMessageLocator).getText();
    }

    public String getProductNotAvailableMessage(){
        return driver.findElement(ProductNotAvailableMessageLocator).getText();
    }

    public void clickOnCheckoutButton() {
        driver.findElement(checkoutButtonLocator).click();
    }

}
