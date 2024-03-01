package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static base.BrowserDriverFactory.getDriver;

public class BasePageObjects {

    static WebDriverWait wait = new WebDriverWait(getDriver(), 10);

    public static void openUrl(String URL) {
        getDriver().get(URL);
    }

    protected static WebElement find(By locator) {
        int maxAttemptsFindLocator = 10;
        WebElement element = null;
        for (int findAttempt = 0; findAttempt < maxAttemptsFindLocator; findAttempt++) {
            try {
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                break;
            } catch (Exception e) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        return element;
    }

    public static void click(By locator) {
        find(locator).click();
    }

    public static void typeText(By locator, String text) {
        find(locator).sendKeys(text);
    }

    public static String getText(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        if (element != null) {
            return element.getText();
        } else {
            System.out.println("the element not visible on the page");
            return "the element not visible on the page";
        }
    }
}