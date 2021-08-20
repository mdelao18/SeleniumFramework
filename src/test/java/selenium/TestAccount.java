package selenium;

import PageObjects.LoginPage;
import PageObjects.Utils;
import dataProviders.UsersProvider;
import io.qameta.allure.Description;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;
import pojo.UserAccount;

public class TestAccount extends BaseClass  {
    public static final String ERROR_EMAIL_AND_PASSWORD_INVALID_MESSAGE = "warning: no match for e-mail address and/or password.";

    //elements
    public By logOutButtonLocator = By.linkText("Logout");
    public By alertMessageLocator = By.xpath("//div[contains(@class, 'alert-danger')]");

    @Description("Validate test login was successful")
    @Test(description = "Test Login Success:  Test_Login_Successful")
    public void Test_Login_Successful(){
        String username = "mdelao18@gmail.com";
        String password = "2020meli123";

        //Go To Login Page
        headerPage().clickOnMyAccount();
        headerPage().clickOnLoginButton();

        //Llenar formulario
        loginPage().EnterEmail(username);
        loginPage().EnterPassword(password);
        loginPage().ClickSubmitButton();

        WebElement logOutButton = driver.findElement(logOutButtonLocator);
        Assert.assertTrue(logOutButton.isDisplayed());
    }

    @Description("Validate that the login is working with non valid credentials")
    @Test(description = "Test Login Not Success: Test_Login_Unsuccessfu")
    public void Test_Login_Unsuccessful(){
        String username = "mdelao18@gmail.com";
        String password = "202012345"; // password incorrecto
        String expectedMessage = "warning: no match for e-mail address and/or password.";

        loginPage().GoTo();
        loginPage().login(username, password);

        WebElement alertMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-danger')]"));
        Assert.assertEquals(expectedMessage.toLowerCase(), alertMessage.getText().toLowerCase().trim());
    }


    @Test (dataProvider = "getUsersData", dataProviderClass = UsersProvider.class)
    public void Test_Login_With_Data(UserAccount testUser){// funciona
        LoginPage loginPage = new LoginPage(driver);

        loginPage.GoTo();
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        if(testUser.isValidAccount())
            Assert.assertTrue(driver.findElement(logOutButtonLocator).isDisplayed());
        else
            Assert.assertEquals(ERROR_EMAIL_AND_PASSWORD_INVALID_MESSAGE.toLowerCase(), driver.findElement(alertMessageLocator).getText().toLowerCase().trim());
    }


     //Proyecto Caso de prueba 1 e-mail random
    @Description("Validate a user can be created successfully")
    @Test(description = "Test Create New User: Test_Create_New_Account")
    public void Test_Create_New_Account(){
        //SETUP
        String firstName = "Melissa";
        String lastName = "De La O";
        //String email = "mdelao18@gmai.com";
        String email = Utils.generateRandomEmail();
        String telephone = "11111";
        String password = "asdf";
        String expectedMessage = "Your Account Has Been Created!";

        //RUN
        registerPage().GoTo();
        registerPage().FillForm(firstName, lastName, email, telephone, password);

        //VALIDATION
        Assert.assertEquals(registerPage().GetConfirmationMessage(), expectedMessage);
    }
    //Proyecto Caso 1 Usuario registrado
    @Description("Validate the e-mail is already registered")
    @Test(description = "This test case verifies the user is already registered")
    public void Test_Duplicated_Email(){

        //SETUP
        String firstName = "Mariano";
        String lastName = "Piedra";
        String email = "mdelao18@gmail.com";
        String telephone = "11111";
        String password = "asdf";
        String expectedMessage = "Warning: E-Mail Address is already registered!";

        //RUN
        registerPage().GoTo();
        registerPage().FillForm(firstName, lastName, email, telephone, password);

        //VALIDATION
        Assert.assertEquals(registerPage().DuplicatedErrorMessage(), expectedMessage);
    }











    /**
     * Open browser
     * Navigate to ...
     * Click to sign in page -> clickOnSignInPageButton()
     * Fill the form  -> fillTheForm(username, password)
     * Click submit -> clickOnSubmitButton()
     * */

 /*
        EJEMPLO DE LISTAS Y WEBELEMENTS SOLOS
        WebElement WishList = driver.findElement(By.linkText("Wish List"));
        ArrayList<> WishListList = driver.findElements(By.linkText("Wish List"));
        */

}