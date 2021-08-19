package PageObjects;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class BaseClass extends PageObjectHandler {


    @Parameters({"browser"})
    @BeforeMethod
    public void beforeMethod(@Optional("Chrome") String browser) throws MalformedURLException,InterruptedException  {

        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "IE":

                //WebDriverManager.edgedriver().setup();
                //driver = new EdgeDriver();
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver =  new ChromeDriver();
                break;
        }
        driver.manage().window().maximize();
        driver.get("https://demo.opencart.com/");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    }


    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        TakeScreenshot();
        driver.close();
        try{
            driver.quit();
        }catch (WebDriverException ex) {
            System.out.println("El browser ya estaba cerrado");
        }
    }

    @Attachment(value = "screenshot", type = "image/png")
    public byte[] TakeScreenshot() {

        return ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.BYTES);
    }
}
