package data;

import java.util.Arrays;

public enum MonthData {
    JANUARY("января", 1),
    FEBRUARY("февраля", 2),
    MARCH("марта", 3),
    APRIL("апреля", 4),
    MAY("мая", 5),
    JUNE("июня", 6),
    JULY("июля", 7),
    AUGUST("августа", 8),
    SEPTEMBER("сентября", 9),
    OCTOBER("октября", 10),
    NOVEMBER("ноября", 11),
    DECEMBER("декабря", 12);

    MonthData (String name, int number) {
        this.name = name;
        this.number = number;
    }
    private String name;
    private int number;

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public static MonthData getDate(String name) {
        return Arrays.stream(MonthData.values())
                .filter((MonthData monthsData) -> monthsData.getName().equals(name))
                .findFirst().get();
    }

    public static MonthData getMonthName(int number) {
        return Arrays.stream(MonthData.values())
                .filter((MonthData monthsData) -> monthsData.getNumber() == number)
                .findFirst().get();
    }
}
