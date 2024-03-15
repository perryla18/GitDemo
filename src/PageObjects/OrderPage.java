package PageObjects;

import AbstractComponent.AbtractComponentClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbtractComponentClass {
    WebDriver driver;
    @FindBy(css = "tr td:nth-child(3)")
    private List<WebElement> ProductNames;
    By produ = By.cssSelector("tr td:nth-child(3)");
    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    public boolean VerifyOderDisplay(String productName) {
        try {
            Thread.sleep(2000); // Chờ 2 giây
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean match = ProductNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
        return match;
    }


}
