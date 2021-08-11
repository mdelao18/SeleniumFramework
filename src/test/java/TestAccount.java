import PageObjects.HeaderPage;
import PageObjects.LoginPage;
import PageObjects.RegisterPage;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TestAccount extends BaseClass {

    /*
        EJEMPLO DE LISTAS Y WEBELEMENTS SOLOS
        WebElement WishList = driver.findElement(By.linkText("Wish List"));
        ArrayList<> WishListList = driver.findElements(By.linkText("Wish List"));
    */

    /*Test Driven Development
     * AS A USER I want to be to able to login properly
     *
     * GIVEN I am at login page
     * AND I log in with juan.piedra@ucreativa and asdf
     * WHEN when user is at dashboard page
     * THEN logout button is displayed
     * */

    /* @Description("Validate that the login with specials credentials")
    @Test(description = "Test Login Success")
    public void Test_Login_Admin() {
        LoginPage loginPage = new LoginPage(driver);
        String username = "admin";
        String password = "12345";
        loginPage.GoTo();
        loginPage.login(username, password);
    }*/


    @Description("Validate test login was successful")
    @Test(description = "Test Login Success")
    public void Test_Login_Successful() {

        HeaderPage headerPage = new HeaderPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        String username = "mdelao18@gmail.com";
        String password = "2020meli123";

        //Ir al log in
        headerPage.clickOnMyAccount();
        headerPage.ClickOnLoginButton();

        //Llenar formulario
        loginPage.EnterEmail(username);
        loginPage.EnterPassword(password);
        loginPage.ClickSubmitButton();

        WebElement logOutButton = driver.findElement(By.linkText("Logout"));
        Assert.assertTrue(logOutButton.isDisplayed());
    }

    @Description("Validate that the login is working with non valid credentials")
    @Test(description = "Test Login  not Success")
    public void Test_Login_Unsuccessful() {

        LoginPage loginPage = new LoginPage(driver);
        String username = "mdelao18@gmail.com";
        String password = "12345"; // pass incorrecto
        String expectedMessage = "warning: no match for e-mail address and/or password.";

        //Ir al log in
        loginPage.GoTo();
        //Llenar formulario
        loginPage.login(username, password);

        WebElement alertMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-danger')]"));
        Assert.assertEquals(expectedMessage.toLowerCase(), alertMessage.getText().toLowerCase().trim());// trim borra espacios a los lados, izq y dere,
    }
    @Description("Validate a user can be created successfully")
    @Test(description = "Test Create New User")
    public void Test_Create_New_Account() {
        //SETUP
        String firstName = "Carlos";
        String lastName = "Esquivel";
        String email = "carlos@esquivel.com";
        String telephone = "85874596";
        String password = "54321";
        String expectedMessage = "Your Account Has Been Created!";
        RegisterPage registerPage = new RegisterPage(driver);

        //RUN
        registerPage.GoTo();
        registerPage.FillForm(firstName, lastName, email, telephone, password);

        //VALIDATION
        Assert.assertEquals(registerPage.GetConfirmationMessage(), expectedMessage);
    }

}

