import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class TestAccount extends BaseClass {

    @Description("Validate test login was successful")
    @Test(description = "Test Login Success")
    public void Test_Login_Successful() {

        String username = "mdelao18@gmail.com";
        String password = "2020meli123";

        //Ir al log in
        //driver.findElement(By.xpath("//a/i[contains(@class, 'fa fa-user')]")).click();// minimizado
        driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a/span[1]")).click();
        driver.findElement(By.linkText("Login")).click();


        //Llenar formulario
        driver.findElement(By.name("email")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("[value='Login']")).click();

        WebElement logOutButton = driver.findElement(By.linkText("Logout"));
        Assert.assertTrue(logOutButton.isDisplayed());

          /*
        EJEMPLO DE LISTAS Y WEBELEMENTS SOLOS
        WebElement WishList = driver.findElement(By.linkText("Wish List"));
        ArrayList<> WishListList = driver.findElements(By.linkText("Wish List"));
        */

    }

    @Description("Validate that the login is working with non valid credentials")
    @Test(description = "Test Login  not Success")
    public void Test_Login_Unsuccessful() {

        String username = "mdelao18@gmail.com";
        String password = "12345"; // pass incorrecto
        String expectedMessage = "warning: no match for e-mail address and/or password.";

        //Ir al log in
        driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a/span[1]")).click();
        driver.findElement(By.linkText("Login")).click();

        //Llenar formulario
        driver.findElement(By.name("email")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("[value='Login']")).click();

        WebElement alertMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-danger')]"));
        Assert.assertEquals(expectedMessage.toLowerCase(), alertMessage.getText().toLowerCase().trim());// trim borra espacios a los lados, izq y dere,
    }
}

