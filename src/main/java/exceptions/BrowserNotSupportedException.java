package exceptions;

public class BrowserNotSupportedException extends Exception {

    public BrowserNotSupportedException(String browser) {
        super(String.format("Browser %s is not supported.", browser));
    }
}
