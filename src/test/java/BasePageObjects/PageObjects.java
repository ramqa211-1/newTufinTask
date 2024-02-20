package BasePageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.example.StepImplementation.driver;

public class PageObjects {
    static int TIMEOUT_SECONDS = 10;
    public static void click(By locator) {
        WebElement element = waitForElementClickable(driver, locator);
        element.click();
    }
    public static void typeText(By locator, String text) {
        WebElement element = waitForElementClickable(driver, locator);
        element.clear();
        element.sendKeys(text);
    }

    private static WebElement waitForElementClickable(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_SECONDS);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}
