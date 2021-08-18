package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SearchPage {

    private WebDriver driver;

    //Elementos
    private By searchLocator = By.name("search");

    public SearchPage(WebDriver _driver){
        this.driver = _driver;
    }

    public void searchInput (String searchInput){
        driver.findElement(searchLocator).sendKeys(searchInput, Keys.ENTER);
    }
}