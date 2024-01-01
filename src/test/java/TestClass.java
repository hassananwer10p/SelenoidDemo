import com.codeborne.selenide.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.CollectionCondition.*;

public class TestClass {

    WebDriver driver;
    @BeforeMethod
    @Parameters("browser")
    public void start(String browser) throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser);
        capabilities.setCapability("enableVNC",true);
        capabilities.setCapability("enableVideo",true);
        capabilities.setCapability("videoName","Selenoid_Demo1.mp4");
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
            }

    @Test
    public void test() throws InterruptedException, MalformedURLException {

        driver.get("https://practicetestautomation.com/practice-test-login/");
        driver.manage().window().maximize();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.Sign_in();
    }



}
