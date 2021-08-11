import PageObjects.SearchPage;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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

}
