package PageObjects;

import AbstractComponent.AbtractComponentClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbtractComponentClass {
    WebDriver driver;
    public LandingPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }
    @FindBy(id = "userEmail")
    WebElement useremail;

    @FindBy(id = "userPassword")
    WebElement userPassword;

    @FindBy(id = "login")
    WebElement login;

    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessage;

    public void goTo () {
        driver.get("https://rahulshettyacademy.com/client");
    }
    public String getErrorMessage() {
        WaitForWebElementAppear(errorMessage);
        return errorMessage.getText();
    }
    public ProductCatalogue loginApp (String email, String password){
        if (!driver.getWindowHandles().isEmpty()){
        useremail.sendKeys(email);
        userPassword.sendKeys(password);
        login.click();}
        else{
            System.out.println("Không tìm thấy cửa sổ trình duyệt để thực hiện đăng nhập.");
            driver.quit();
        }
        return new ProductCatalogue(driver);
    }
}
