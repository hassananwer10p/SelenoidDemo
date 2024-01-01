import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    WebDriver driver;

    @FindBy(id = "username")
    WebElement user;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id = "submit")
    WebElement SubmitBtn;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void Sign_in() throws InterruptedException {

        Thread.sleep(2000);
        user.sendKeys("student");
        Thread.sleep(2000);
        password.sendKeys("Password123");

        SubmitBtn.click();

    }
}