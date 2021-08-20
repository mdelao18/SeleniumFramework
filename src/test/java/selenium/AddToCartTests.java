package selenium;

import PageObjects.HeaderPage;
import PageObjects.HomePage;
import PageObjects.ProductPage;
import PageObjects.ShoppingCartPage;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AddToCartTests extends BaseClass {


    @Description ("Validate That add to cart is working")
    @Test
    public void  Test_Add_To_Cart_Functionality(){
        /**
         * opciones de navegación
         * 1. search
         * 2. home add to cart  *****
         * 3. home -> Product Page -> add to cart
         * 4. contruir la URL
         * */
        int quantity = 5;

        HeaderPage headerPage = new HeaderPage(driver);
        HomePage homePage = new HomePage(driver);
        ProductPage productPage = new ProductPage(driver);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);

        String name = homePage.selectFirstProductAndGetName();
        Assert.assertTrue(productPage.isTitleDisplayed(name));
        productPage.SetQuantity(quantity);
        productPage.clickAddButton();
        Assert.assertTrue(productPage.isAlertSuccessDisplayed());
        headerPage.clickOnCartButton();
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Assert.assertTrue(shoppingCartPage.isProductNameDisplayed(name), "Title was not displayed");
        Assert.assertEquals(shoppingCartPage.getProductQuantity(), quantity, "Quantity is not matching");
    }

    @Description("Validate several items added to the cart")
    @Test
    public void Test_Several_Items_Added_To_The_Cart(){
        homePage().selectProductByName("MacBook");
        productPage().SetQuantity(2);
        productPage().clickAddButton();
        homePage().GoTo();
        homePage().selectProductByName("iPhone");
        productPage().SetQuantity(5);
        productPage().clickAddButton();
        headerPage().clickOnCartButton();
        Assert.assertEquals(shoppingCartPage().getAmountOfShoppingCartRows(), 2, "Expected to get 2 rows");
    }

    //Proyecto caso de prueba 2
    @Description("Products can be added at the shopping cart ")
    @Test(description = "This test case verifies the products can be added at the shopping cart")
    @Parameters({"searchCriteria"})
    public void Test_Item_Added(@Optional("Macbook Air") String searchCriteria) {
        String productAddedMessage = "Success: You have added MacBook Air to your shopping cart!\n" +
                "×";

        productPage().addProductInSearch(searchCriteria);
        productPage().clickOnProductSearched();
        productPage().SetQuantity(3);
        productPage().clickAddButton();
        Assert.assertEquals(shoppingCartPage().getProductAddedInCartMessage(), productAddedMessage);

    }

    //Proyecto caso de prueba 2
    @Description("Products aren't available to be added at the shopping cart ")
    @Test(description = "This test case verifies the unavailable products can't be added at the shopping cart")
    @Parameters({"searchCriteria"})
    public void Test_Items_Not_Available(@Optional("Macbook Air") String searchCriteria) {

        String productNotAvailableMessage = "Products marked with *** are not available in the desired quantity or not in stock!\n" +
                "×";

        productPage().addProductInSearch(searchCriteria);
        productPage().clickOnProductSearched();
        productPage().SetQuantity(15000);
        productPage().clickAddButton();
        headerPage().clickOnCartButton();
        shoppingCartPage().clickOnCheckoutButton();
        Assert.assertEquals(shoppingCartPage().getProductNotAvailableMessage(), productNotAvailableMessage);
    }


    //Proyecto caso de prueba 3
    @Description("Product prices are the same even in different currency")
    @Test(description = "This test case verifies product prices are the same even in different currency")
    @Parameters({"product"})
    public void Test_Validate_ProductPrice(@Optional("Macbook") String product, boolean dollarPrice, boolean poundsPrice, boolean euroPrice) {
        productPage().addProductInSearch(product);
        productPage().clickOnProductSearched();


    }


}