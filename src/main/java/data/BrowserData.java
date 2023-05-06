package data;

public enum BrowserData {
    CHROME("chrome"),
    FIREFOX("firefox"),
    OPERA("opera");

    BrowserData(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }
}
