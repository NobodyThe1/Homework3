package pages;

import annotations.Path;
import org.openqa.selenium.WebDriver;
import pageObject.AbsPageObject;

public abstract class AbsPage<T> extends AbsPageObject<T> {

    private String baseUrl = System.getProperty("base.url", "https://otus.ru");

    public AbsPage (WebDriver driver) {
        super(driver);
    }

    private String getPath() {
        Path path = getClass().getAnnotation(Path.class);
        if(path != null) {
            return path.value();
        }
        return "";
    }
    public void open() {
        driver.get(baseUrl + getPath());
    }
}
