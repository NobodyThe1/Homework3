
package waiters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;


import java.time.Duration;
public class Waiter {

    private WebDriver driver;

    public Waiter(WebDriver driver) {
        this.driver = driver;
    }

    public boolean waitForCondition (ExpectedCondition condition) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        try {
            webDriverWait.until(condition); {
                return true;
            }
        } catch (Exception ignored) {
            return false;
        }
    }
}
