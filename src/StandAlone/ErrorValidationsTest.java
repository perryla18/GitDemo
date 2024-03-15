package StandAlone;

import ADB.Resourse.BaseTest;
import PageObjects.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class ErrorValidationsTest extends BaseTest {
    @Test
    public void submitOrder()  {

        landingPage.loginApp("anshika@gmail.com", "Iamkin000");
        AssertJUnit.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    }

    public void ProductErrorValidation(){
        String productName = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginApp("rahulshetty@gamil.com", "Iamking@000");
        List<WebElement> products = productCatalogue.getProductsList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.gotoCartPage();
        boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
        Assert.assertFalse(match);
    }

}

