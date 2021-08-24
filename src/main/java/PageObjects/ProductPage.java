package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage extends BasePage{
    //elementos
    public String ProductTitleSelector = "//h1[text()='<name>']";
    public  By ProductQuantityInputSelector = By.id("input-quantity");
    public By AddButtonSelector = By.id("button-cart");
    public By AlertSuccess = By.cssSelector(".alert-success");
    public By clickOnProductSearchedLocator = By.xpath("//*[@id=\"content\"]/div[3]/div/div/div[1]/a/img");
    public By addProductInSearchLocator = By.name("search");

    public By productPriceLocator = By.xpath("//*[@id=\"content\"]/div/div[2]/ul[2]/li[1]/h2");


    public ProductPage(WebDriver _driver){
        super(_driver);
    }

    public boolean isTitleDisplayed(String name){
        return driver.findElement(By.xpath(ProductTitleSelector.replace("<name>", name))).isDisplayed();

     }
    public void SetQuantity (int quantity){
        driver.findElement(ProductQuantityInputSelector).clear();
        driver.findElement(ProductQuantityInputSelector).sendKeys("" + quantity);
    }

    public void clickAddButton (){
        driver.findElement(AddButtonSelector).click();
    }

    public boolean isAlertSuccessDisplayed(){
        return driver.findElement(AlertSuccess).isDisplayed();
    }

    //Proyecto
    public void clickOnProductSearched(){
        driver.findElement(clickOnProductSearchedLocator).click();
    }

    public void addProductInSearch (String _product) {
        driver.findElement(addProductInSearchLocator).sendKeys(_product,Keys.ENTER);
    }
    public String getProductPrice (){
       return driver.findElement(productPriceLocator).getText();
    }
}
