import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import static org.apache.logging.log4j.LogManager.getLogger;

public class OtusTest {

    static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterEach
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void firstTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);
        driver.get("https://duckduckgo.com/");
        driver.findElement(By.cssSelector("#search_form_input_homepage")).sendKeys("ОТУС");
        driver.findElement(By.cssSelector("#search_button_homepage")).click();
        Assertions.assertTrue(driver.findElement(By.cssSelector("#r1-0 > div > h2 > a")).
                getText().contains("Онлайн‑курсы для профессионалов, дистанционное обучение"));
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
        driver.manage().window().maximize();
        String login = "otus-test@mail.ru";
        String password = "1Testtest+";
        driver.get("https://otus.ru/");
        driver.findElement(By.cssSelector(".header3__button-sign-in-container")).click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        driver.findElement(By.cssSelector("div:nth-child(3) > input")).sendKeys(login);
        driver.findElement(By.cssSelector("div:nth-child(4) > input")).sendKeys(password);
        driver.findElement(By.cssSelector("div:nth-child(5) > button")).submit();
        Logger logger = LogManager.getLogger(OtusTest.class);
        Set<Cookie> cookieList = driver.manage().getCookies();
        for (Cookie cookie : cookieList) {
            logger.info(cookie);
        }
    }
}