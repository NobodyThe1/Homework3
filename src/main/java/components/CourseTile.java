package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import data.MonthData;
import pages.AnyPage;

import java.time.LocalDate;
import java.util.stream.Stream;

public class CourseTile extends AbsComponent<CourseTile> {

    public CourseTile(WebDriver driver) {
        super(driver);
    }

    @FindBy (css = "[class='lessons'] .lessons__new-item-start, [class='lessons'] .lessons__new-item-bottom > .lessons__new-item-time")
    private List<WebElement> courseList;

    public void getEarlierLaterCourse(boolean isEarlier) {

        BinaryOperator<LocalDate> reduceImpl = null;
        if (isEarlier) {
            reduceImpl = (LocalDate x, LocalDate y) -> x.isBefore(y) ? y : x;
        } else {
            reduceImpl = (LocalDate x, LocalDate y) -> x.isBefore(y) ? y : y;
        }

        courseList.stream()
                .filter((WebElement element) -> !element.getText().equals("О дате старта будет объявлено позже"))
                .map((WebElement element) -> {
                    String dateString = element.getText().replaceAll("^С", "").trim();
                    dateString = dateString.replaceAll("\\s+\\d+\\s.*", "");

                    String month = dateString.split("\\s+")[1];
                    dateString = dateString.replaceAll("[а-я]+",
                            String.format("%d", MonthData.getDate(month).getNumber())) + " " + LocalDate.now().getYear();
                    return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("d M yyyy"));
                })
                .reduce(reduceImpl)
                .map((LocalDate localDate) -> {
                    String finalDate = localDate.format(DateTimeFormatter.ofPattern("d M"));
                    return finalDate.replaceAll("\\s+\\d+$", String.format("%s", MonthData.getMonthName(localDate.getMonthValue())));
                })
                .map((String finalDate) -> {
                    WebElement element = driver.findElement(By.xpath(String.format(courseTitleLocator, finalDate)));
                    return element;
                })
                .get()
                .click();
    }

    public void moveToCourse(String title) {
        moveAndPerform(driver.findElement(By.xpath(String.format(courseTitleLocator, title))));
    }
    public void findCourseByTitle(String title) {
        moveAndClick(driver.findElement(By.xpath(String.format(courseTitleLocator, title))));
    }
}
