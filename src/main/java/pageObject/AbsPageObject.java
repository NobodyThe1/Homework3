package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import waiters.Waiter;

public abstract class AbsPageObject<T> {

    protected WebDriver driver;
    protected Actions actions;
    protected Waiter waiter;

    public AbsPageObject (WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.waiter = new Waiter(driver);

        PageFactory.initElements(driver,  this);
    }

    protected String courseTitleLocator = "//*[contains(@class, 'lessons__new-item-title')][contains (text(), 's')]";

    protected void moveAndClick (WebElement element) {
        waiter.waitForCondition(ExpectedConditions.visibilityOf(element));
        actions.moveToElement(element);
        actions.click();
    }

    protected void moveAndPerform (WebElement element) {
        waiter.waitForCondition(ExpectedConditions.visibilityOf(element));
        actions.moveToElement(element);
        actions.click()
                .build()
                .perform();
    }
}
