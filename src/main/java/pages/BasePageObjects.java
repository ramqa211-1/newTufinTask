package pages;
import com.thoughtworks.gauge.datastore.ScenarioDataStore;
import locators.SauceDemoSiteLocators;
import base.BrowserDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
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

    public static void calculateItemPricesFromCheckoutPage() {
        List<WebElement> itemPrices = BrowserDriverFactory.getDriver().findElements(SauceDemoSiteLocators.itemPriceFromCheckoutPage);
        double totalSum = 0;
        double taxRate = 0.08;
        for (WebElement itemPrice : itemPrices) {
            String priceText = itemPrice.getText().replace("$", "");
            double price = Double.parseDouble(priceText);
            totalSum += price;
        }
        double tax = totalSum * taxRate;
        tax = Double.parseDouble(String.format("%.2f", tax));
        System.out.println("the Tax rate is " + tax + "");
        ScenarioDataStore.put("totalSum", totalSum);
        ScenarioDataStore.put("tax", tax);
    }

    public static void verifyTaxRate() {
        double totalSum = (double) ScenarioDataStore.get("totalSum");
        double tax = (double) ScenarioDataStore.get("tax");
        double expectedTaxApplyOnTotalSumItems = totalSum * 0.08;
        expectedTaxApplyOnTotalSumItems = Double.parseDouble(String.format("%.2f", expectedTaxApplyOnTotalSumItems));  // Format expectedTaxApplyOnTotalSumItems to two decimal places
        System.out.println("the Tax amount in this purchase is " + expectedTaxApplyOnTotalSumItems + "");
        Assert.assertEquals(tax, expectedTaxApplyOnTotalSumItems, "Tax calculation is correct!");
    }
}