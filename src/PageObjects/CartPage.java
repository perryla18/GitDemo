package PageObjects;

import AbstractComponent.AbtractComponentClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbtractComponentClass {
    WebDriver driver;
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = ".totalRow button")
    WebElement checkoutEle;
    @FindBy(css = "div[class='cartSection'] h3")
    List<WebElement> cartProducts;
    public boolean VerifyProductDisplay(String productName) {
        // Thêm một khoảng thời gian chờ để đảm bảo rằng giao diện người dùng đã được cập nhật
        try {
            Thread.sleep(2000); // Chờ 2 giây
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
    }


    public CheckoutPage gotoCheckout() {
        checkoutEle.click();
        return new CheckoutPage(driver);
    }
        }
