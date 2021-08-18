package selenium;

import PageObjects.SearchPage;
import PageObjects.SearchResultsPage;
import dataProviders.SearchProvider;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pojo.SearchData;
import PageObjects.BaseClass;

public class TestSearch extends BaseClass {

    @Description("Validate the search option")
    @Test(description = "Search option")
    @Parameters({"searchCriteria", "expectedResult"})
    public void Validate_Search(@Optional("macbook") String searchCriteria, @Optional("3") String expectedResult){
        int results = Integer.parseInt(expectedResult);

        //POM
        SearchPage searchPage = new SearchPage(driver);
        searchPage.searchInput(searchCriteria);

        //WebElement searchInput = driver.findElement(By.name("search"));
        //searchInput.sendKeys(searchCriteria, Keys.ENTER);

        Assert.assertTrue(driver.getCurrentUrl().contains("search="+searchCriteria));
        Assert.assertEquals(getResults(), results,
                "Expecting " + expectedResult + " results, but got " + getResults());
    }

    @Description("Validate the empty results")
    @Test(description = "Search with empty results")

    public void Test_Empty_Results() {
        String searchCriteria = "Star Wars";
        int expectedResult = 0;

        //POM
        SearchPage searchPage = new SearchPage(driver);
        searchPage.searchInput(searchCriteria);

        //WebElement searchInput = driver.findElement(By.name("search"));
        //searchInput.sendKeys(searchCriteria, Keys.ENTER);

        Assert.assertEquals(getResults(), expectedResult,
                "Expecting " + expectedResult + " results, but got " + getResults());
    }

    public int getResults(){
       return  driver.findElements(By.cssSelector(".product-thumb")).size();
    }



    @Test(dataProvider = "getSearchDataFromJson",dataProviderClass = SearchProvider.class)
    public void  Test_Search_WithData(SearchData testData){
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        WebElement searchInput = driver.findElement(By.name("search"));
        searchInput.sendKeys(testData.getSearchCriteria());

        driver.findElement(By.xpath("//div[@id='search']/span/button")).click();

        if(testData.getExpectedResults() > 0){
            Assert.assertEquals(searchResultsPage.getResultsCount(), testData.getExpectedResults());
        }
        else{
            Assert.assertTrue(searchResultsPage.isNoResultsVisible());
        }

    }

    /*@DataProvider(name = "searchEntries")
    public Object[][] dataProvider(){
        return new Object[][]{
                {"macbook",3},
                {"star wars", 0}
        };
     */
    }


    /**
     * String = "Juan"
     *
     * String[] = ["Juan", "Pablo", "Piedra"]
     *
     *
     * String[][]
     * Nombre Apellido Correo
     * Juaun    Piedra  juan@piedra
     *
     * */



