package AbstractComponent;

import PageObjects.CartPage;
import PageObjects.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbtractComponentClass {
    WebDriver driver;

    public AbtractComponentClass(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public void WaitForElementAppear(By findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void WaitForWebElementAppear(WebElement findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }

    public void WaitForElementDisappear(WebElement ele){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.invisibilityOf(ele));
    }

    @FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
    WebElement cartHeader;

    public CartPage gotoCartPage() {
        cartHeader.click();
        return new CartPage(driver);
    }
    @FindBy(css = "[routerlink*='myorders']")
    WebElement cartOrder;

    public OrderPage gotOrderPage(){
        WaitForWebElementAppear(cartOrder);
        cartOrder.click();
        OrderPage orderPage = new OrderPage(driver);
        return orderPage;
    }
}
