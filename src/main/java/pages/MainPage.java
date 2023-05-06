package pages;

import annotations.Path;
import org.openqa.selenium.WebDriver;

@Path("/")
public class MainPage extends AbsPage<MainPage> {

    public MainPage (WebDriver driver) {
        super(driver);
    }
}
