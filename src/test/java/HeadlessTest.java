import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HeadlessTest {

    private WebDriver driver;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void init() {
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
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void close() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    @Test
    public void headlessTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://duckduckgo.com/");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#search_form_input_homepage")));
        driver.findElement(By.cssSelector("#search_form_input_homepage")).sendKeys("ОТУС");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#search_button_homepage")));
        driver.findElement(By.cssSelector("#search_button_homepage")).click();
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#searchbox_homepage")));
        Assertions.assertTrue(driver.findElement(By.cssSelector("#r1-0 > div.ikg2IXiCD14iVX7AdZo1 > h2")).
                getText().contains("Онлайн‑курсы для профессионалов, дистанционное обучение"));
    }
}