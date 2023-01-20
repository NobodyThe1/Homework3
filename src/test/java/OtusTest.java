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

    WebDriver driver;


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
            driver.quit();
        }
    }

    @Test
    public void firstTest() throws InterruptedException {
        driver.close();
        driver.quit();

        ChromeOptions options = new ChromeOptions();
         options.addArguments("--window-size=1920,1200")
                .addArguments("--ignore-certificate-errors")
                .addArguments("--silent")
                .addArguments("--test-type")
                .addArguments("--disable-gpu")
                .addArguments("--no-first-run")
                .addArguments("--no-default-browser-check")
                .addArguments("--ignore-certificate-errors")
                .addArguments("--start-maximized")
                .addArguments("--headless");
        ChromeDriver newDriver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(newDriver, Duration.ofSeconds(10));

        newDriver.get("https://duckduckgo.com/");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#search_form_input_homepage")));
        newDriver.findElement(By.cssSelector("#search_form_input_homepage")).sendKeys("ОТУС");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#search_button_homepage")));
        newDriver.findElement(By.cssSelector("#search_button_homepage")).click();
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#searchbox_homepage")));
        Assertions.assertTrue(newDriver.findElement(By.cssSelector("#r1-0 > div.ikg2IXiCD14iVX7AdZo1 > h2")).
                getText().contains("Онлайн‑курсы для профессионалов, дистанционное обучение"));
        
        newDriver.close();
        newDriver.quit();
    }

    @Test
    public void secondTest() {
        driver.manage().window().fullscreen();
        driver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");
        driver.findElement(By.cssSelector("li.portfolio-item2.content[data-id='id-2']")).click();
        driver.findElement(By.cssSelector("div.pp_hoverContainer")).isDisplayed();
    }

    @Test
    public void thirdTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        String login = "otus-test@mail.ru";
        String password = "1Testtest+";
        driver.get("https://otus.ru/");
        driver.findElement(By.cssSelector(".header3__button-sign-in-container")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".new-input[name='email']")));
        driver.findElement(By.cssSelector(".new-input[name='email']")).sendKeys(login);
        driver.findElement(By.cssSelector(".new-input[name='password']")).sendKeys(password);
        driver.findElement(By.cssSelector(".new-button[type='submit']")).submit();
        Logger logger = LogManager.getLogger(OtusTest.class);
        Set<Cookie> cookieList = driver.manage().getCookies();
        for (Cookie cookie : cookieList) {
            logger.info(cookie);
        }
    }
}
