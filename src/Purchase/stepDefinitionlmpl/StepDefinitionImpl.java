package Purchase.stepDefinitionlmpl;

import ADB.Resourse.BaseTest;
import PageObjects.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImpl extends BaseTest {
    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public CartPage cartPage;
    public ConfirmationPage confirmationPage;
    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException {
        landingPage = launchApplication();
    }
    @Given("^Logged in with username (.+) and password (.+)$") // Đối với String có value thì thay bằng ký tự (.+) và thê ^, $ đầu cuối)
    public void Logged_in_with_username_and_password(String username, String password){
        productCatalogue = landingPage.loginApp(username, password);
    }

    @When("^I add product (.+) to Cart$")
    public void I_add_product_to_Cart (String productName){
        List<WebElement> products = productCatalogue.getProductsList();
        productCatalogue.addProductToCart(productName);
    }

    @When("^Checkout (.+) and submit the order$")
    public void Checkout_and_submit_the_order(String productName){
        cartPage = productCatalogue.gotoCartPage();
        boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);
        cartPage.gotoCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.selectCountry("india");
        confirmationPage = checkoutPage.submitOrdder();
    }

    @Then("{string} message is displayed on ConfirmationPage")
    public void message_is_displayed_on_confirmation_page(String string) {
        // Thực hiện kiểm tra xem thông báo có hiển thị đúng không
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
        driver.close();
    }
    @Then("{string} message is displayed")
    public void message_is_displayed(String string) throws Throwable{
        Assert.assertEquals(string, landingPage.getErrorMessage());
        driver.close();
    }
}
