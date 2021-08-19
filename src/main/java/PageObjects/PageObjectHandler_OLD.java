package PageObjects;

import PageObjects.HeaderPage;
import PageObjects.LoginPage;
import PageObjects.RegisterPage;
import PageObjects.SearchResultsPage;
import okhttp3.internal.http2.Header;
import org.openqa.selenium.WebDriver;

public class PageObjectHandler_OLD {

    public WebDriver driver;

    private HeaderPage headerPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private SearchResultsPage searchResultsPage;

    public PageObjectHandler_OLD(){}

    public HeaderPage headerPage(){
        if(this.headerPage == null)
            headerPage =  new HeaderPage(driver);
        return headerPage;
    }

    public LoginPage loginPage(){
        if(this.loginPage == null)
            loginPage =  new LoginPage(driver);
        return loginPage;
    }

    public RegisterPage registerPage(){
        if(this.registerPage == null)
            registerPage =  new RegisterPage(driver);
        return registerPage;
    }

    public SearchResultsPage searchResultsPage(){
        if(this.searchResultsPage == null)
            searchResultsPage =  new SearchResultsPage(driver);
        return searchResultsPage;
    }


}