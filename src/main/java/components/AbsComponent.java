package components;

import org.openqa.selenium.WebDriver;
import pageObject.AbsPageObject;

public class AbsComponent<T> extends AbsPageObject<T> {

    public AbsComponent(WebDriver driver) {
        super(driver);
    }
}
