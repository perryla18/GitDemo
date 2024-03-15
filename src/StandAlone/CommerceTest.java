package StandAlone;


import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class CommerceTest  {
    @Test
    public void main() throws IOException {
        System.setProperty("webdriver.chrome.driver","chromedriver-win64//chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().deleteAllCookies();

        driver.get("https://rahulshettyacademy.com/client");

        String productName = "ZARA COAT 3";

        driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");
        driver.findElement(By.id("login")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

        WebElement prod = products.stream()
                .filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)
                )
                .findFirst()
                .orElse(null);
        if (prod != null){
            prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
        }
        else {
            System.out.println("Don't find this item");
            driver.close();
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("/html/body/app-root/app-dashboard/ngx-spinner"))));// Đợi đến khi nhừng xoay
        driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
        driver.findElement(By.xpath("//button[@routerlink='/dashboard/myorders']]")).click();

        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match = cartProducts.stream().allMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
        driver.findElement(By.cssSelector(".totalRow button")).click();

        Actions actions= new Actions(driver);
        actions.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "India").build().perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Select Country']")));

        driver.findElement(By.xpath("(//button[@class='ta-item list-group-item ng-star-inserted']) [2]")).click();
        driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();

//        File photoshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//        File destfile = new File("C:\\Users\\perry.la\\thankyou.png");
//        FileUtils.copyFile(photoshot, destfile);

        System.out.println(driver.findElement(By.cssSelector(".hero-primary")).getText());
        driver.quit();
    }
}
