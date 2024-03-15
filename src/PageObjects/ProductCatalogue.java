package PageObjects;

import AbstractComponent.AbtractComponentClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbtractComponentClass {
    WebDriver driver;
    public ProductCatalogue (WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = ".mb-3")
    List<WebElement> products;

    @FindBy(xpath = "/html/body/app-root/app-dashboard/ngx-spinner")
            WebElement spinner;

    By ProductsBY = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.id("toast-container");
    public List<WebElement> getProductsList(){
        WaitForElementAppear(ProductsBY);
        return products;
    }
    public WebElement getProductByName(String productName){
        WebElement prod = getProductsList().stream().filter(product ->
                product.findElement(By.cssSelector("b")).getText().equals(productName)).
                findFirst().
                orElse(null);
        return prod;
    }

    public void addProductToCart(String productName) {
        WebElement Pro = getProductByName(productName);
        Pro.findElement(addToCart).click();
        WaitForElementAppear(toastMessage);
        WaitForElementDisappear(spinner);
    }


}
