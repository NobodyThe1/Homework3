package factories;

import exceptions.BrowserNotSupportedException;
import org.openqa.selenium.WebDriver;


public interface IWebDriverFactory {
    WebDriver create() throws BrowserNotSupportedException;
}

