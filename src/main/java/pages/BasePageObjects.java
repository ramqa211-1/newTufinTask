package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static base.BrowserDriverFactory.getDriver;

public class BasePageObjects {

    public static void openUrl(String URL) {
        getDriver().get(URL);
    }

    protected static WebElement find(By locator) {
        try {
            return getDriver().findElement(locator);
        } catch (Exception e) {
            int count = 0;
            while (count < 10) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                count++;
            }
            return getDriver().findElement(locator);
        }
    }

    public static void click(By locator) {
        find(locator).click();
    }

    public static void typeText(By locator, String text) {
        find(locator).sendKeys(text);
    }

    public static String getText(By locator) {
        String textPage;
        textPage = getDriver().findElement(locator).getText();
        if (getDriver().findElements(locator).isEmpty()) {
            return "THE ELEMENT IS EMPTY";
        } else {
            return textPage;
        }
    }
}