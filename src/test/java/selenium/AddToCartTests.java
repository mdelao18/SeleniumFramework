package selenium;

import PageObjects.HeaderPage;
import PageObjects.HomePage;
import PageObjects.ProductPage;
import PageObjects.ShoppingCartPage;
import dataProviders.ProductProvider;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;
import pojo.Products;

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


    //****************************************************************************
    //Proyecto caso de prueba 2
    @Description("Products can be added / If products unavailable checkout can't be made ")
    @Test(description = "This test case verifies products can be added but if they are unavailable the checkout can't be made")
    public void Test_Items_Not_Available(@Optional("Macbook Air") String searchCriteria) {

        String productNotAvailableMessage = "Products marked with *** are not available in the desired quantity or not in stock!\n" +
                "×";
        String productAddedMessage = "Success: You have added MacBook Air to your shopping cart!\n" +
                "×";

        productPage().addProductInSearch(searchCriteria);
        productPage().clickOnProductSearched();
        productPage().SetQuantity(15000);
        productPage().clickAddButton();
        Assert.assertEquals(shoppingCartPage().getProductAddedInCartMessage(), productAddedMessage);

        headerPage().clickOnCartButton();
        shoppingCartPage().clickOnCheckoutButton();
        Assert.assertEquals(shoppingCartPage().getProductNotAvailableMessage(), productNotAvailableMessage);
    }


    //Proyecto caso de prueba 3
    @Description("Product prices comparison in different currencies")
    @Test(dataProvider = "getUsersDataFromJson", dataProviderClass = ProductProvider.class)
    public void Test_Validate_ProductPrice(Products testData) {
        productPage().addProductInSearch(testData.getProduct());
        productPage().clickOnProductSearched();

        headerPage().goToEuroCurrency();
        Assert.assertEquals(testData.getEuroPrice(), Double.parseDouble(productPage().getProductPrice().replace("€","")));

        headerPage().goToPoundSterlingCurrency();
        Assert.assertEquals(testData.getPoundSterlingPrice(), Double.parseDouble(productPage().getProductPrice().replace("£","")));

        headerPage().goToDollarCurrency();
        Assert.assertEquals(testData.getDollarsPrice(), Double.parseDouble(productPage().getProductPrice().replace("$","")));
    }
}