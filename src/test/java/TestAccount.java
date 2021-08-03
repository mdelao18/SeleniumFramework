import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class TestAccount {
    @Description("Validate test login was successful")
    @Test
    public void Test_Login_Successful() {

        String username = "mdelao18@gmail.com";
        String password = "2020meli123";

        //String pathToDriver = Test.class.getResource("/chromedriver.exe").getPath();
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://demo.opencart.com/");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        //Ir al log in
        //driver.findElement(By.xpath("//a/i[contains(@class, 'fa fa-user')]")).click();// minimizado
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a/span[1]")).click();
        driver.findElement(By.linkText("Login")).click();


        //Llenar formulario
        driver.findElement(By.name("email")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("[value='Login']")).click();

        WebElement logOutButton = driver.findElement(By.linkText("Logout"));
        Assert.assertTrue(logOutButton.isDisplayed());

        TakesScreenshot(driver);

        driver.close();
        driver.quit();

          /*
        EJEMPLO DE LISTAS Y WEBELEMENTS SOLOS
        WebElement WishList = driver.findElement(By.linkText("Wish List"));
        ArrayList<> WishListList = driver.findElements(By.linkText("Wish List"));
        */

    }

    @Description("Validate that the login is working with non valid credentials")
    @Test
    public void Test_Login_Unsuccessful() {

        String username = "mdelao18@gmail.com";
        String password = "12345"; // pass incorrecto
        String expectedMessage = "warning: no match for e-mail address and/or password.";

        //String pathToDriver = Test.class.getResource("/chromedriver.exe").getPath();
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://demo.opencart.com/");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        //Ir al log in
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a/span[1]")).click();
        driver.findElement(By.linkText("Login")).click();


        //Llenar formulario
        driver.findElement(By.name("email")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("[value='Login']")).click();

        WebElement alertMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-danger')]"));
        Assert.assertEquals(expectedMessage.toLowerCase(), alertMessage.getText().toLowerCase().trim());// trim borra espacios a los lados, izq y dere,
        TakesScreenshot(driver);
        driver.close();
        driver.quit();
    }

    @Attachment(value = "screenshot", type = "image/png")
    public byte[] TakesScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
