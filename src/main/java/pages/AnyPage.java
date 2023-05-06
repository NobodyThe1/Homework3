package pages;

import annotations.Path;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AnyPage extends AbsPage<AnyPage>{

    public AnyPage(WebDriver driver) {
        super(driver);
    }

    public void rightPageShouldBeOpened(String title) {
        Assertions.assertEquals((driver.findElement(By.xpath(String.format(courseTitleLocator, title))).getText()), title);
    }
}
