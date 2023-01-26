import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import static org.apache.logging.log4j.LogManager.getLogger;

public class OtusTest {

    private WebDriver driver;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void init() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void close() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    @Test
    public void fullScreenTest() {
        driver.manage().window().fullscreen();
        driver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("document.querySelector('li.portfolio-item2.content[data-id='id-2']').click();");
        driver.findElement(By.cssSelector("div.pp_hoverContainer")).isDisplayed();
    }

    @Test
    public void loginTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        String login = "otus-test@mail.ru";
        String password = "1Testtest+";
        driver.get("https://otus.ru/");
        driver.findElement(By.cssSelector(".header3__button-sign-in-container")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[type='text'][name='email']:not(.hide)")));
        driver.findElement(By.cssSelector("[type='text'][name='email']:not(.hide)")).sendKeys(login);
        driver.findElement(By.cssSelector("[name='password']")).sendKeys(password);
        driver.findElement(By.cssSelector(".new-button[type='submit']")).submit();
        Logger logger = LogManager.getLogger(OtusTest.class);
        Set<Cookie> cookieList = driver.manage().getCookies();
        for (Cookie cookie : cookieList) {
            logger.info(cookie);
        }
    }
}
