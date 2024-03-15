package StandAlone;
import ADB.Resourse.BaseTest;
import PageObjects.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOderTest extends BaseTest {
    String productName = "ZARA COAT 3";
    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String, String> input) throws IOException {

        ProductCatalogue productCatalogue = landingPage.loginApp(input.get("email"), input.get("password"));

        List<WebElement> products = productCatalogue.getProductsList();
            productCatalogue.addProductToCart(productName);

        CartPage cartPage = productCatalogue.gotoCartPage();
        boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);

        cartPage.gotoCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.selectCountry("india");


        ConfirmationPage confirmationPage = checkoutPage.submitOrdder();
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

    }
    @Test(dependsOnMethods = {"submitOrder"})
    public void OrderHistoryTest() {

        ProductCatalogue productCatalogue = landingPage.loginApp("anshika@gmail.com", "Iamking@000");
        OrderPage orderPage = productCatalogue.gotOrderPage();
        Assert.assertTrue(orderPage.VerifyOderDisplay(productName));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//ADB//Resourse//PurchaseOder.json");

        return new Object[][] {{data.get(0)}, {data.get(1)}};
    }
//    @DataProvider
//    public Object[][] getData(){
//        return new Object[] [] {{"anshika@gmail.com", "Iamking@000", "ZARA COAT 3"}, {"shetty@gmail.com", "Iamking@000", "ADIDAS ORIGINAL"}};
//    }

}

