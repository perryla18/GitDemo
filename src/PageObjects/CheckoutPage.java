package PageObjects;

import AbstractComponent.AbtractComponentClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbtractComponentClass {
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement country;

    @FindBy(xpath = "(//button[@class='ta-item list-group-item ng-star-inserted']) [2]")
    WebElement selectCountry;

    @FindBy(css = ".btnn.action__submit.ng-star-inserted")
    WebElement submit;

    By results= By.xpath("//input[@placeholder='Select Country']");

    public void selectCountry(String countryName) {
        Actions actions= new Actions(driver);
        actions.sendKeys(country, countryName).build().perform();
        WaitForElementAppear(results);
        WaitForWebElementAppear(selectCountry);
        selectCountry.click();

    }
    public ConfirmationPage submitOrdder(){
        submit.click();
        return new ConfirmationPage(driver);
    }
}
